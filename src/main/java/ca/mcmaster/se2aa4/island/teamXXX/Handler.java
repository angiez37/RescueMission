package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.Commands;
import ca.mcmaster.se2aa4.island.teamXXX.Island;
import ca.mcmaster.se2aa4.island.teamXXX.Navigate;
import ca.mcmaster.se2aa4.island.teamXXX.Test;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Handler {
    private final Logger logger = LogManager.getLogger();
    private Commands commandChosen;
    //private Test test;
    private Navigate navigate;


    private Integer batteryThresh = 100;

    public Handler() {this.navigate = new Navigate();}

    public void setCommand(Commands command) { this.commandChosen = command; }

    public Commands getCommand() { return this.commandChosen; }

    public JSONObject makeDecision(Drone drone, Island island) {
        if (drone.getBattery() < batteryThresh) {
            logger.info("battery low");
            return drone.stop();
        }
        return this.navigate.search(drone, island, this);
    }
    
}
