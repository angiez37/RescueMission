package ca.mcmaster.se2aa4.island.teamXXX;
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
    public String echoRight() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        decision.put("action", "echo");
        decision.put("parameters", parameters.put("direction", "" + this.compass.getRight()));

        return decision.toString();
    }

    //Radar sensor sends an echo signal to the left of the drone.
    public String echoLeft() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        decision.put("action", "echo");
        decision.put("parameters", parameters.put("direction", "" + this.compass.getLeft()));

        return decision.toString();
    }

    //Radar sensor sends an echo signal to the front of the drone.
    public String echoForward() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        decision.put("action", "echo");
        decision.put("parameters", parameters.put("direction", "" + this.getHeading()));

        return decision.toString();
    }

    //Photo scanner scans below the drone
    public String scan() {
        JSONObject decision = new JSONObject();
        decision.put("action", "scan");
        return decision.toString();
    }

    //Drone flies forward in its current direction(heading)
    public String fly() {
        JSONObject decision = new JSONObject();
        decision.put("action", "fly");
        this.compass.updatePrevHeading();
        return decision.toString();
    }

    //Drone changes direction to the left of its current heading
    public String turnLeft() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        decision.put("action", "heading");
        decision.put("parameters", parameters.put("direction", "" + this.compass.turnLeft()));

        return decision.toString();
    }

    //Drone changes direction to the right of its current heading
    public String turnRight() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        decision.put("action", "heading");
        decision.put("parameters", parameters.put("direction", "" + this.compass.turnRight()));

        return decision.toString();
    }

    //Drone stops
    public String stop() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        return decision.toString();
    }
}
