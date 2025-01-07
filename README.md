# Swiatelka

## How to run

Simply copy the repositorium and run in root directory

```bash

./gradlew run --args="input.json output.json"
```
where `input.json` and `output.json` are file directories.


## I/O format

Given the example input (file `input1.json`):

```json
{
    "commands": {
        "0": [
            {
                "carName": "Car1",
                "directionFrom": "NORTH",
                "directionTo": "SOUTH"
            }
        ],
        "1": [
            {
                "carName": "Car2",
                "directionFrom": "EAST",
                "directionTo": "WEST"
            }
        ]
    }
}
```

it will:
- in the initial state of the crossroad there will be only one car (`Car1`)
- after the first change of the lights another car will be added (`Car2`)


The resulting output looks like this:
```json
{
  "1" : [ "Car1" ],
  "2" : [ "Car2" ]
}
```
It shows on which step the car has successfully exited the crossroad.


## How it works
There are four double-lane roads (from `NORTH`, `SOUTH`, `EAST`, `WEST`). Each car can:
- drive forward
- drive to the left road
- drive to the right road


On each step of the simulation traffic lights can:
- allow only driving forward/right
- force car to stop
- allow only driving to the left

The simulation runs untill no cars are left **and** no cars will be added in the future.

Additional features added:
 - GitHub actions
 - *technically* additional phases of the traffic lights
 - additional lane 
