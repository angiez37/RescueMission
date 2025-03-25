package ca.mcmaster.se2aa4.island.teamXXX.island;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.island.Island;
import ca.mcmaster.se2aa4.island.teamXXX.Handler;
import ca.mcmaster.se2aa4.island.teamXXX.island.Signal;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Commands;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Direction;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GroundNavigate implements NavigateMap {
    private boolean groundFound = false;
    private Integer iteration = 0;
    private Integer outOfRangeIteration = 0;
    private Integer groundIteration = 0;

    private Direction groundDirection = null; 


    public GroundNavigate() {}

    private final Logger logger = LogManager.getLogger();

    public boolean getGroundFound() {
        return groundFound;
    }

    public JSONObject search(Drone drone, Island island, Handler handler) {
        if (this.iteration == 0) {

            this.iteration++;
            handler.setCommand(Commands.ECHOFORWARD);
            return drone.echoForward();

        } else if (island.getForwardRange() > 0 && island.getForward() == Signal.OUTOFRANGE) {
            return echoForwardScan(drone, island, handler);
        } 
        

            else if (island.getForwardRange() > 0 && island.getForward() == Signal.GROUND) {
                return skipWaterGround(drone, island, handler);
        
            }

        else if (island.getForwardRange() == 0 && island.getForward() == Signal.GROUND && !groundFound) {
            return moveOnceForward(drone, island, handler);
         
        } else {
            handler.setCommand(Commands.STOP);
            return drone.stop();
        }
     //   return handler.makeDecision(drone, island);
    }

    public Direction getStartDirection(){
        return groundDirection;
    }

    private JSONObject echoForwardScan(Drone drone, Island island , Handler handler)    {
        if (this.outOfRangeIteration == 0) {
            // First fly forward
            this.outOfRangeIteration++;
            handler.setCommand(Commands.SCAN);
            return drone.scan();
        } else if (this.outOfRangeIteration == 1) {
            // Then scan
            this.outOfRangeIteration++;
            handler.setCommand(Commands.FLY);
            return drone.fly();
        } else if (this.outOfRangeIteration == 2) {
            // Then scan
            this.outOfRangeIteration++;
            handler.setCommand(Commands.SCAN);
            return drone.scan();
        } else if (this.outOfRangeIteration == 3) {
            this.outOfRangeIteration++;
            handler.setCommand(Commands.TURNRIGHT);
            return drone.turnRight();
        } else if (this.outOfRangeIteration == 4) {
            this.outOfRangeIteration++;
            handler.setCommand(Commands.FLY);
            return drone.fly();
        }else if (this.outOfRangeIteration == 5) {
            this.outOfRangeIteration++;
            handler.setCommand(Commands.TURNRIGHT);
            return drone.turnRight();
        } else if (this.outOfRangeIteration == 6) {
            this.outOfRangeIteration++;
            handler.setCommand(Commands.TURNRIGHT);
            return drone.turnRight();
        }  else if (this.outOfRangeIteration == 7) {
            this.outOfRangeIteration++;
            handler.setCommand(Commands.TURNRIGHT);
            return drone.turnRight();

        //ends drone movement at east 
        } else if (this.outOfRangeIteration == 8) {
            return setInitialToEast(drone, handler);
        }
        else{
            handler.setCommand(Commands.STOP);
            return drone.stop();

        }

    }

    private JSONObject setInitialToEast(Drone drone, Handler handler) {
        this.outOfRangeIteration = 0;
        this.iteration = 0;
        handler.setCommand(Commands.SCAN);
        return drone.scan();
    }
    private JSONObject skipWaterGround(Drone drone, Island island, Handler handler){
        if (this.groundIteration == 0) {
            this.groundIteration++;
            handler.setCommand(Commands.SCAN);
            return drone.scan();
        } else if (this.groundIteration < island.getForwardRange() + 1) {
            this.groundIteration++;
            handler.setCommand(Commands.FLY);
    
            return drone.fly();
        } else {
            this.groundIteration = 0;
            handler.setCommand(Commands.ECHOFORWARD);
            return drone.echoForward();
        }

    }

    private JSONObject moveOnceForward(Drone drone, Island island, Handler handler){
        if (this.groundIteration == 0) {
            this.groundIteration++;
            handler.setCommand(Commands.SCAN);
            return drone.scan();
        } else if (this.groundIteration == 1) {
            this.groundIteration++;
            handler.setCommand(Commands.FLY);
            logger.info("ground found");
            return drone.fly();
        }else if (this.groundIteration == 2) {
            this.groundIteration++;
            this.groundDirection = drone.getHeading();
            logger.info("starting direction, {}", this.groundDirection);
            handler.setCommand(Commands.SCAN);
            groundFound = true;
            return drone.scan();
        }
        return handler.makeDecision(drone, island);
    }
    
}
