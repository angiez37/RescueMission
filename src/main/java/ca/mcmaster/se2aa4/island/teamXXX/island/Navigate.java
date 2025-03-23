package ca.mcmaster.se2aa4.island.teamXXX.island;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.island.Island;
import ca.mcmaster.se2aa4.island.teamXXX.Handler;

import org.json.JSONObject;
public interface Navigate {
    public JSONObject search(Drone drone, Island island, Handler handler);
    public boolean getGroundFound();
}
