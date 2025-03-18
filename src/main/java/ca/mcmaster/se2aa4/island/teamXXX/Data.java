package ca.mcmaster.se2aa4.island.teamXXX;
import ca.mcmaster.se2aa4.island.teamXXX.Commands;
import org.json.JSONArray;
import org.json.JSONObject;

public class Data {
    private Commands command;
    private Integer cost;
    private Boolean status;
    private JSONObject extras;
    // extras attributes
    private JSONArray creeks;
    private JSONArray biomes;
    private JSONArray sites;
    private Signal found;
    private Integer range;

    public Data(Commands command, Integer cost, JSONObject extras, Boolean status) {
        this.command = command;
        this.cost = cost;
        this.status = status;
        this.extras = extras;
        updatedAttributes(this.extras);
    }
    
    private void updatedAttributes(JSONObject extras) {

        try { this.creeks = extras.getJSONArray("creeks"); }
        catch (Exception e) { this.creeks = null; }

        try { this.biomes = extras.getJSONArray("biomes"); }
        catch (Exception e) { this.biomes = null; }

        try { this.sites = extras.getJSONArray("sites"); }
        catch (Exception e) { this.sites = null; }

        try {
            String foundAttribute = extras.getString("found");
            if (foundAttribute != null) {
                if (foundAttribute.equals("OUT_OF_RANGE")) { this.found = Signal.OUTOFRANGE; }
                else { this.found = Signal.GROUND; }
            }
        } catch (Exception e) { this.found = null; }

        try { this.range = extras.getInt("range"); }
        catch (Exception e) { this.range = null; }
    }

    public Commands getCommand() { return this.command; }

    public Integer getCost() { return this.cost; }

    public Boolean getStatus() { return this.status; }

    public JSONArray getCreeks() { return this.creeks; }

    public JSONArray getBiomes() { return this.biomes; }

    public JSONArray getSites() { return this.sites; }

    public Signal getFound() { return this.found; }

    public Integer getRange() { return this.range; }
}
