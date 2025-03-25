package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Commands;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.island.Island;
import ca.mcmaster.se2aa4.island.teamXXX.island.NavigateHandler;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Handler class manages drone decisions
public class Handler {
    private final Logger logger = LogManager.getLogger();
    private Commands commandChosen;
    private NavigateHandler navigate;


    private Integer batteryThresh = 100;

    public Handler() {this.navigate = new NavigateHandler();}

    //set commands as they are performed by the drone
    public void setCommand(Commands command) { this.commandChosen = command; }

    //get commands performed by the drone
    public Commands getCommand() { return this.commandChosen; }

    public JSONObject makeDecision(Drone drone, Island island) {
        //stop the drone below the battery threshold so it can return to safety
        if (drone.getBattery() < batteryThresh) {
            logger.info("battery low");
            return drone.stop();
        }
        return this.navigate.search(drone, island, this);
    }
    
}
