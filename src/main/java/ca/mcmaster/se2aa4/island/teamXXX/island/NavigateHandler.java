package ca.mcmaster.se2aa4.island.teamXXX.island;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.island.Island;
import ca.mcmaster.se2aa4.island.teamXXX.Handler;
import ca.mcmaster.se2aa4.island.teamXXX.island.Signal;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Commands;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NavigateHandler {
    private Navigate groundNavigate = new GroundNavigate();
    private Navigate creekNavigate = new CreekNavigate();
    private boolean groundFound = false;
    
    private static final Logger logger = LogManager.getLogger(NavigateHandler.class);

    public NavigateHandler() {}
    
    public JSONObject search(Drone drone, Island island, Handler handler) {
        groundFound = this.groundNavigate.getGroundFound();
        if (!groundFound) {
            logger.info("searching for ground");
            return this.groundNavigate.search(drone, island, handler);
        }
        logger.info("searching for creek");
        return this.creekNavigate.search(drone, island, handler);
    }
    
}
