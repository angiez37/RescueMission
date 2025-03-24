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

public class NavigateHandler {
    private Navigate groundNavigate = new GroundNavigate();
    private Navigate creekNavigate;  //old code
    private boolean groundFound = false;
    private boolean initializeCreekSearch = false;

    
    private static final Logger logger = LogManager.getLogger(NavigateHandler.class);

   // public NavigateHandler() {
        //this.groundNavigate = new GroundNavigate();
    //}
    
    public JSONObject search(Drone drone, Island island, Handler handler) {
        groundFound = this.groundNavigate.getGroundFound();
        if (!groundFound) {
            logger.info("searching for ground");
            return this.groundNavigate.search(drone, island, handler);
        }
        if(!initializeCreekSearch){
            //gotta change this following line 
            Direction groundDirection = ((GroundNavigate)groundNavigate).getStartDirection(); 
            logger.info("naviagte handler - Start direction for creek navi is {}", groundDirection);
            this.creekNavigate = new CreekNavigate(groundDirection);
            initializeCreekSearch = true;
        }
        //logger.info("searching for creek");
        return this.creekNavigate.search(drone, island, handler);
    }
    
}
