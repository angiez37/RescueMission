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
            } else if (this.outOfRangeIteration == 8) {
                this.outOfRangeIteration = 0;
                this.iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }/*else if (this.outOfRangeIteration == 3) {
                //now echo right
                this.outOfRangeIteration++;
                handler.setCommand(Commands.ECHORIGHT);
                return drone.echoRight();
            } else if (this.outOfRangeIteration == 4) {
                //now echo left
                this.outOfRangeIteration++;
                handler.setCommand(Commands.ECHOLEFT);
                return drone.echoLeft();
            } else if (this.outOfRangeIteration == 5) {
                this.outOfRangeIteration = 0;
                this.iteration = 0;
                if (island.getLeftRange() > island.getRightRange()) {
                    handler.setCommand(Commands.TURNLEFT);
                    return drone.turnLeft();
                } else {
                    handler.setCommand(Commands.TURNRIGHT);
                    return drone.turnRight();
                }*/
        } else if (island.getForwardRange() > 0 && island.getForward() == Signal.GROUND) {
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
        } else if (island.getForwardRange() == 0 && island.getForward() == Signal.GROUND && !groundFound) {
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
        } else {
            handler.setCommand(Commands.STOP);
            return drone.stop();
        }
        return handler.makeDecision(drone, island);
    }

    public Direction getStartDirection(){
        return groundDirection;
    }
    
}
