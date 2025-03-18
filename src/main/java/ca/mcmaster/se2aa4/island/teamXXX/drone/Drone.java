package ca.mcmaster.se2aa4.island.teamXXX.drone;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Commands;
import org.json.JSONObject;
public class Drone {

    private Integer battery;
    private Compass compass;

    public Drone(Integer battery, Direction heading) {
        this.battery = battery;
        this.compass = new Compass(heading);
    }

    public Direction getHeading() { return this.compass.getHeading(); }

    public Direction getInitialHeading() { return this.compass.getInitialHeading(); }

    public Direction getPrevHeading() { return  this.compass.getPrevHeading(); }

    public Integer getBattery() { return this.battery; }

    public void updateBattery(Integer drain) { this.battery -= drain; }

    //Radar sensor sends an echo signal to the right of the drone.
    public JSONObject echoRight() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        decision.put("action", "echo");
        decision.put("parameters", parameters.put("direction", "" + this.compass.getRight()));

        return decision;
    }

    //Radar sensor sends an echo signal to the left of the drone.
    public JSONObject echoLeft() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        decision.put("action", "echo");
        decision.put("parameters", parameters.put("direction", "" + this.compass.getLeft()));

        return decision;
    }

    //Radar sensor sends an echo signal to the front of the drone.
    public JSONObject echoForward() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        decision.put("action", "echo");
        decision.put("parameters", parameters.put("direction", "" + this.getHeading()));

        return decision;
    }

    //Photo scanner scans below the drone
    public JSONObject scan() {
        JSONObject decision = new JSONObject();
        decision.put("action", "scan");
        return decision;
    }

    //Drone flies forward in its current direction(heading)
    public JSONObject fly() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        this.compass.updatePrevHeading();
        return decision;
    }

    //Drone changes direction to the left of its current heading
    public JSONObject turnLeft() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        decision.put("action", "heading");
        decision.put("parameters", parameters.put("direction", "" + this.compass.turnLeft()));

        return decision;
    }

    //Drone changes direction to the right of its current heading
    public JSONObject turnRight() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        decision.put("action", "heading");
        decision.put("parameters", parameters.put("direction", "" + this.compass.turnRight()));

        return decision;
    }

    //Drone stops
    public JSONObject stop() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        return decision;
    }
}
