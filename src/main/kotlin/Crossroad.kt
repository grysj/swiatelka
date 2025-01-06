package org.example

class Crossroad {

    var roadsMap = HashMap<Direction, Road>()

    init {
        val directions = listOf<Direction>(Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST)
        for (direction in directions) {
            val road = Road()
            roadsMap[direction] = road
        }
        for (direction in listOf<Direction>(Direction.NORTH, Direction.SOUTH)) {
            roadsMap[direction]!!.setPriority()
        }
    }

    fun addCar(car: Car, directionFrom: Direction) {
        val destinationRoad = roadsMap[directionFrom.getDestination(car.direction)]
        car.addListener(RoadCountListener(destinationRoad!!))
        destinationRoad.addCount()
        roadsMap[directionFrom]!!.addCar(car)
    }

    fun runStep() {
        for (road in roadsMap.values) {
            road.step()
        }
        for (road in roadsMap.values) {
            road.lightSignal.nextSignal()
        }
    }

    fun carsTotal(): Int {
        var count = 0
        for (road in roadsMap.values) {
            count += road.getCarsCount()
        }
        return count
    }
}