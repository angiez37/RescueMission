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
    //private Integer turnIteration = 0;
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

        }
        
        if (island.getForward() == Signal.GROUND && island.getLeft() == Signal.OUTOFRANGE && island.getForwardRange() == 0) {
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
        } else if ((island.getForward() == Signal.GROUND && island.getLeft() == Signal.OUTOFRANGE && island.getForwardRange() > 0 && island.getRightRange() == 0)){// || (island.getRight() == Signal.GROUND && island.getLeft() == Signal.OUTOFRANGE && island.getForward() == Signal.OUTOFRANGE)) {
            if (rightIteration == 0) {
                rightIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (rightIteration == 1) {
                rightIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (rightIteration == 2) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 3) {
                rightIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (rightIteration == 4) {
                rightIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (rightIteration == 5) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 6) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 7) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 8) {
                rightIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } else if ((island.getForward() == Signal.GROUND && island.getLeft() == Signal.GROUND && island.getRight() == Signal.GROUND) || (island.getForward() == Signal.OUTOFRANGE && island.getRight() == Signal.OUTOFRANGE)) {
            if (leftIteration == 0) {
                leftIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (leftIteration == 1) {
                leftIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (leftIteration == 2) {
                leftIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (leftIteration == 3) {
                leftIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (leftIteration == 4) {
                leftIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (leftIteration == 5) {
                leftIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (leftIteration == 6) {
                leftIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } else if ((island.getForward() == Signal.GROUND && island.getLeft() == Signal.GROUND && island.getRight() == Signal.GROUND && island.getLeftRange() > 0)) {
            if (leftIteration == 0) {
                leftIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (leftIteration == 1) {
                leftIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (leftIteration == 2) {
                leftIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (leftIteration == 3) {
                leftIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } 
        } else if ((island.getRight() == Signal.GROUND && island.getLeft() == Signal.OUTOFRANGE && island.getForward() == Signal.GROUND) ) {
            if (leftIteration == 0) {
                leftIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (leftIteration == 1) {
                leftIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } 
        } else if ((island.getForward() == Signal.OUTOFRANGE && island.getRight() == Signal.GROUND)) {
            if (rightIteration == 0) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 1) {
                rightIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } else {
            logger.info("drone stopping");
            handler.setCommand(Commands.STOP);
            return drone.stop();
        }

        return handler.makeDecision(drone, island);


    }else{

        if (iteration == 0) {
            iteration++;
            handler.setCommand(Commands.ECHOFORWARD);
            return drone.echoForward();
        } else if (iteration == 1) {
            iteration++;
            handler.setCommand(Commands.ECHOLEFT);
            return drone.echoLeft();
        } else if (iteration == 2) {
            iteration++;
            handler.setCommand(Commands.ECHORIGHT);
            return drone.echoRight();

        }
        if (island.getForward() == Signal.GROUND && island.getRight() == Signal.OUTOFRANGE && island.getForwardRange() == 0) {
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
        } else if ((island.getForward() == Signal.GROUND && island.getRight() == Signal.OUTOFRANGE && island.getForwardRange() > 0 && island.getLeftRange() == 0)){// || (island.getRight() == Signal.GROUND && island.getLeft() == Signal.OUTOFRANGE && island.getForward() == Signal.OUTOFRANGE)) {
            if (leftIteration == 0) {
                leftIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (leftIteration == 1) {
                leftIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (leftIteration == 2) {
                leftIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (leftIteration == 3) {
                leftIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (leftIteration == 4) {
                leftIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (leftIteration == 5) {
                leftIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (leftIteration == 6) {
                leftIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (leftIteration == 7) {
                leftIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (leftIteration == 8) {
                leftIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } else if ((island.getForward() == Signal.GROUND && island.getRight() == Signal.GROUND && island.getLeft() == Signal.GROUND) || (island.getForward() == Signal.OUTOFRANGE && island.getLeft() == Signal.OUTOFRANGE)) {
            if (rightIteration == 0) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 1) {
                rightIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (rightIteration == 2) {
                rightIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (rightIteration == 3) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 4) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 5) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 6) {
                rightIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } else if ((island.getForward() == Signal.GROUND && island.getRight() == Signal.GROUND && island.getLeft() == Signal.GROUND && island.getRightRange() > 0)) {
            if (rightIteration == 0) {
                rightIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (rightIteration == 1) {
                rightIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (rightIteration == 2) {
                rightIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (rightIteration == 3) {
                rightIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } 
        } else if ((island.getLeft() == Signal.GROUND && island.getRight() == Signal.OUTOFRANGE && island.getForward() == Signal.GROUND) ) {
            if (rightIteration == 0) {
                rightIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (rightIteration == 1) {
                rightIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } 
        } else if ((island.getForward() == Signal.OUTOFRANGE && island.getLeft() == Signal.GROUND)) {
            if (leftIteration == 0) {
                leftIteration++;
                handler.setCommand(Commands.TURNLEFT);
                return drone.turnLeft();
            } else if (leftIteration == 1) {
                leftIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } else {
            logger.info("drone stopping");
            handler.setCommand(Commands.STOP);
            return drone.stop();
        }

        return handler.makeDecision(drone, island);


    }
    
}

}




/*if (rightIteration == 0) {
                rightIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (rightIteration == 1) {
                rightIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (rightIteration == 2) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 3) {
                rightIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (rightIteration == 4) {
                rightIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (rightIteration == 5) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 6) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 7) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 8) {
                rightIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } */

    /*if (rightIteration == 0) {
                rightIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (rightIteration == 1) {
                rightIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (rightIteration == 2) {
                rightIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else if (rightIteration == 3) {
                rightIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (rightIteration == 4) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 5) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 6) {
                rightIteration++;
                handler.setCommand(Commands.TURNRIGHT);
                return drone.turnRight();
            } else if (rightIteration == 7) {
                rightIteration = 0;
                iteration = 0;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } */