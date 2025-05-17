# Rescue mission
- Student: [Angel Saka](https://angiez37.github.io)
- Course: SFWRENG 2AA4 [Software Design I - Introduction to Software Development]

## Product Description

This product is an _exploration command center_ for the [Island](https://ace-design.github.io/island/) serious game. 

This project is a Rescue Drone System. This system supports rescue missions of individuals by 
- Locating people in unknown terrain
- Finding a safe place where a rescue team can be sent close to the people to rescue.

The current product line focuses on rescue missions in marine environments. Specifically, the goal is 
to rescue people who survived shipwrecks and found shelter in islands. 

Based on satellite imaging, we know that there is an island more or less in the center of the search 
area (a rectangular grid). We also know that people took shelter somewhere on the island in a place 
called the Emergency Site. The island’s coast is not easy to approach, and a rescue mission coming 
by boat will only be able to land at some specific inlets (Creeks). There are around ten inlets that are suitable 
for landing on the coast. The emergency site is usually a flat and open space on land. 

### Objectives
- Locate the Island
- Find Emergency Sites
- Locate Creeks

### Drone Commands
- Fly: Requst the drone to fly forward
- Heading: Request the drone to turn left or right by indicating the new direction to face.
- Radar: The drone's radar sensors send an echo signal to detect land
- Photo Scanner: Scan the area below to recognize different biomes and points of interest
- Stop: Stop the drone's movement

### Technologies
- Java, Maven, JSON, JUnit

## How to compile, run and deploy

### Compiling the project:

```mvn clean package```

This creates one jar file in the `target` directory, named after the team identifier (i.e., team 00 uses `team00-1.0.jar`).

### Run the project

Execution for testing purposes:

```mvn exec:java -q -Dexec.args="./maps/map03.json"```

It creates three files in the `outputs` directory:

- `_pois.json`: the location of the points of interests
- `Explorer_Island.json`: a transcript of the dialogue between the player and the game engine
- `Explorer.svg`: the map explored by the player, with a fog of war for the tiles that were not visited.
