package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.Drone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Decision {
    private final Logger logger = LogManager.getLogger();

    private Integer batteryThresh = 100;
    private Integer cost = 1500;
    public Decision() {}

    public String makeDecision(Drone drone) {
        while (drone.getBattery() > batteryThresh) {
            drone.fly();
            drone.updateBattery(cost);
            logger.info("The drone just flew");

            drone.echoLeft();
            drone.updateBattery(cost);
            logger.info("The drone just echoed to the left");

            drone.turnLeft();
            drone.updateBattery(cost);
            logger.info("The drone just turned left");

            drone.fly();
            drone.updateBattery(cost);
            logger.info("The drone just flew");
        }
        drone.stop();
        logger.info("The drone just stopped");
        return "the drone just flew";

    }
    
}
