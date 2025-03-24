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



public class CreekNavigate implements Navigate {
    private Integer iteration = 0;
    private Integer rightIteration = 0;
    private Integer leftIteration = 0;
    private Integer turnIteration = 0;
    private Integer emptyFrontIteration = 0;
    private boolean emptyCheck = false;
    private Integer forwardIteration = 0;
    private boolean groundFound = true;
    private boolean clockwiseSearch = false; 
    private Direction dirSearch;
    private final Logger logger = LogManager.getLogger();


    public boolean getGroundFound() {
        return groundFound;
    }

    public CreekNavigate(Direction groundDirection) {
        this.dirSearch = groundDirection;
        if( groundDirection == Direction.EAST || this.dirSearch == Direction.NORTH){
            this.clockwiseSearch = true;

        }
        
    }

    public JSONObject search(Drone drone, Island island, Handler handler) {
        if(clockwiseSearch){     
        if (iteration == 0) {
            iteration++;
            handler.setCommand(Commands.ECHOFORWARD);
            return drone.echoForward();
        } else if (iteration == 1) {
            iteration++;
            handler.setCommand(Commands.ECHORIGHT);
            return drone.echoRight();
        } else if (iteration == 2) {
            iteration++;
            handler.setCommand(Commands.ECHOLEFT);
            return drone.echoLeft();

        } else if (island.getForward() == Signal.GROUND && island.getForwardRange() == 0) {
            if (forwardIteration == 0) {
                forwardIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (forwardIteration == 1) {
                forwardIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } else if (island.getForward() == Signal.GROUND && island.getForwardRange() > 0) {
            if (forwardIteration == 0) {
                forwardIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (forwardIteration < island.getForwardRange() + 1) {
                forwardIteration++;
                handler.setCommand(Commands.FLY);
                logger.info("successfully skipped water");
                return drone.fly();
            } else if (forwardIteration == island.getForwardRange() + 1) {
                forwardIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } else if(island.getForward() == Signal.OUTOFRANGE && ((island.getRight() == Signal.OUTOFRANGE && island.getRightRange() < 1) || (island.getLeft() == Signal.OUTOFRANGE && island.getLeftRange() < 1))) {
            logger.info("drone reached the end of the island");
            handler.setCommand(Commands.STOP);
            return drone.stop();
        }else if ((drone.getHeading() == Direction.EAST && turnIteration == 0 && island.getForward() == Signal.OUTOFRANGE && island.getRight() == Signal.GROUND && island.getRightRange() == 0 && emptyFrontIteration == 0)) {
            logger.info("flying before turning from east to west");
            if (forwardIteration == 0) {
                forwardIteration++;
               // iteration = 0;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (forwardIteration == 1) {
                forwardIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } else if ((drone.getHeading() == Direction.EAST && turnIteration == 0 && island.getForward() == Signal.OUTOFRANGE && island.getRightRange() > 0 && emptyFrontIteration == 0)) {
            logger.info("turning from east to west");
            emptyFrontIteration = 1;
        } else if (island.getForward() == Signal.OUTOFRANGE && turnIteration == 0){
            if (forwardIteration == 0) {
                forwardIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (forwardIteration == 1) {
                forwardIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            }else if (forwardIteration == 2) {
                forwardIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (forwardIteration == 3) {
                forwardIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (forwardIteration == 4) {
                forwardIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (forwardIteration == 5) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (forwardIteration == 6) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (forwardIteration == 7){
                forwardIteration = 0;
                turnIteration = 1;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } 
        /*else if (island.getForward() == Signal.OUTOFRANGE && turnIteration == 0) {
            if (forwardIteration == 0) {
                forwardIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            }  else if (forwardIteration == 1) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (forwardIteration == 2) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (forwardIteration == 3) {
                forwardIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (forwardIteration == 4) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                logger.info("DEBUG - No conditions met: heading={}, forward={}, right={}, left={}, ranges=[fwd={},right={},left={}], iterations=[main={},fwd={},turn={},empty={}]",
                drone.getHeading(),
                island.getForward(),
                island.getRight(),
                island.getLeft(),
                island.getForwardRange(),
                island.getRightRange(),
                island.getLeftRange(),
                iteration,
                forwardIteration,
                turnIteration,
                emptyFrontIteration);
                return drone.turnRight();
            } else if (forwardIteration == 5) {
                forwardIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (forwardIteration == 6) {
                forwardIteration = 0;
                turnIteration = 1;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
                */
        else if ((drone.getHeading() == Direction.WEST && island.getForward() == Signal.OUTOFRANGE && island.getLeft() == Signal.GROUND && island.getLeftRange() == 0 && emptyFrontIteration == 1)){
            logger.info("flying before turning from west to east");
            if (forwardIteration == 0) {
                forwardIteration++;
                //iteration = 0;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (forwardIteration == 1) {
                forwardIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
                } else if ((drone.getHeading() == Direction.WEST && island.getForward() == Signal.OUTOFRANGE && island.getLeftRange() > 0 && emptyFrontIteration == 1)) {
            logger.info("turning from west to east");
            emptyFrontIteration = 0;
        } else if (island.getForward() == Signal.OUTOFRANGE && turnIteration == 1){
            if (forwardIteration == 0) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (forwardIteration == 1) {
                forwardIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            }else if (forwardIteration == 2) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (forwardIteration == 3) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (forwardIteration == 4) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (forwardIteration == 5) {
                forwardIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (forwardIteration == 6) {
                forwardIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (forwardIteration == 7){
                forwardIteration = 0;
                turnIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }/*if (forwardIteration == 0) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (forwardIteration == 1) {
                forwardIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (forwardIteration == 2) {
                forwardIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (forwardIteration == 3) {
                forwardIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (forwardIteration == 4) {
                forwardIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (forwardIteration == 5) {
                forwardIteration++;

                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (forwardIteration == 6 || forwardIteration == 7|| forwardIteration == 8) {
                forwardIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if(forwardIteration == 9) {
                forwardIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (forwardIteration == 10){
                forwardIteration = 0;
                turnIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }*/
        } else {
            logger.info("drone stopping");
            handler.setCommand(Commands.STOP);
            return drone.stop();
        }
        } 
        return handler.makeDecision(drone, island);
    } 

}