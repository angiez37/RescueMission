package ca.mcmaster.se2aa4.island.teamXXX.island;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.Data;
import ca.mcmaster.se2aa4.island.teamXXX.island.Signal;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Commands;
import org.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Island {
    private final Logger logger = LogManager.getLogger();
    private Data response;

    private Drone drone;
    private Signal forward;
    private Integer forwardRange;
    private Signal right;
    private Integer rightRange;
    private Signal left;
    private Integer leftRange;

    public Island(Drone drone) {
        this.drone = drone;
        this.response = null;
        this.forward = Signal.UNKNOWN;
        this.forwardRange = 0;
        this.right = Signal.UNKNOWN;
        this.rightRange = 0;
        this.left = Signal.UNKNOWN;
        this.leftRange = 0;
    }


    public Integer getForwardRange() {
        return this.forwardRange;
    }

    public Integer getLeftRange() {
        return this.leftRange;
    }

    public Integer getRightRange() {
        return this.rightRange;
    }

    private void updateGetForwardRange() {
        if (this.response.getRange() != null) {
            this.forwardRange = this.response.getRange();
        }
    }

    private void updateGetLeftRange() {
        if (this.response.getRange() != null) {
            this.leftRange = this.response.getRange();
        }
    }

    private void updateGetRightRange() {
        if (this.response.getRange() != null) {
            this.rightRange = this.response.getRange();
        }
    }

    public Signal getForward() {
        return this.forward;
    }

    public Signal getLeft() {
        return this.left;
    }

    public Signal getRight() {
        return this.right;
    }

    private void updateGetForward() {
        if (this.response.getFound() == Signal.GROUND) {
            this.forward = Signal.GROUND;
        } else {
            this.forward = Signal.OUTOFRANGE;
        }
    }

    private void updateGetLeft() {
        if (this.response.getFound() == Signal.GROUND) {
            this.left = Signal.GROUND;
        } else {
            this.left = Signal.OUTOFRANGE;
        }
    }

    private void updateGetRight() {
        if (this.response.getFound() == Signal.GROUND) {
            this.right = Signal.GROUND;
        } else {
            this.right = Signal.OUTOFRANGE;
        }
    }

    public void update(Data response) {
        this.response = response;
        Commands commandChosen = this.response.getCommand();

        if (commandChosen == Commands.ECHOFORWARD) {
            updateGetForward();
            updateGetForwardRange();
        } else if (commandChosen == Commands.ECHOLEFT) {
            updateGetLeft();
            updateGetLeftRange();
        } else if (commandChosen == Commands.ECHORIGHT) {
            updateGetRight();
            updateGetRightRange();
        } else if (commandChosen == Commands.SCAN) {
            JSONArray creeks = this.response.getCreeks();
            JSONArray sites = this.response.getSites();
        }
    }

    
}
