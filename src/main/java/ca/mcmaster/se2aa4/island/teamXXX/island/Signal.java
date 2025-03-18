package ca.mcmaster.se2aa4.island.teamXXX.island;

public enum Signal {
    GROUND,
    OCEAN,
    OUTOFRANGE,
    UNKNOWN;

    @Override
    public String toString() {
        return name(); // Returns the name of the enum constant as a String
    }
}

