package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CrossroadTest {
    private lateinit var crossroad: Crossroad

    @BeforeEach
    fun setup() {
        crossroad = Crossroad()
    }

    @Test
    fun `initial state should contain all four roads with correct priorities`() {
        assertEquals(4, crossroad.roadsMap.size)
        assertTrue(crossroad.roadsMap.containsKey(Direction.NORTH))
        assertTrue(crossroad.roadsMap.containsKey(Direction.SOUTH))
        assertTrue(crossroad.roadsMap.containsKey(Direction.EAST))
        assertTrue(crossroad.roadsMap.containsKey(Direction.WEST))

        assertTrue(crossroad.roadsMap[Direction.NORTH]!!.lightSignal is PriorityRoadLight)
        assertTrue(crossroad.roadsMap[Direction.SOUTH]!!.lightSignal is PriorityRoadLight)
        assertTrue(crossroad.roadsMap[Direction.EAST]!!.lightSignal is NonPriorityRoadLight)
        assertTrue(crossroad.roadsMap[Direction.WEST]!!.lightSignal is NonPriorityRoadLight)
    }

    @Test
    fun `addCar should add car to correct road and set up proper listener`() {
        val car = Car("test", CarDirection.FORWARD)
        val sourceDirection = Direction.NORTH

        crossroad.addCar(car, sourceDirection)

        assertEquals(1, crossroad.roadsMap[sourceDirection]!!.getCarsCount())
        assertEquals(1, crossroad.roadsMap[Direction.SOUTH]!!.awaitingCount)
    }

    @Test
    fun `runStep should update all roads and change light signals`() {
        val car1 = Car("test1", CarDirection.FORWARD)
        val car2 = Car("test2", CarDirection.RIGHT)
        crossroad.addCar(car1, Direction.NORTH)
        crossroad.addCar(car2, Direction.EAST)

        val initialStates = crossroad.roadsMap.mapValues { it.value.lightSignal.state }

        crossroad.runStep()

        crossroad.roadsMap.forEach { (direction, road) ->
            assertNotEquals(
                initialStates[direction],
                road.lightSignal.state,
                "Light at $direction should have changed"
            )
        }
    }

    @Test
    fun `carsTotal should return correct sum of all cars`() {
        val car1 = Car("test1", CarDirection.FORWARD)
        val car2 = Car("test2", CarDirection.RIGHT)
        val car3 = Car("test3", CarDirection.LEFT)

        crossroad.addCar(car1, Direction.NORTH)
        crossroad.addCar(car2, Direction.EAST)
        crossroad.addCar(car3, Direction.WEST)

        assertEquals(3, crossroad.carsTotal())
    }

    @Test
    fun `cars should move through crossroad correctly`() {
        val car = Car("test", CarDirection.FORWARD)
        crossroad.addCar(car, Direction.NORTH)

        crossroad.roadsMap[Direction.NORTH]!!.lightSignal.state = LightType.FR

        assertEquals(1, crossroad.carsTotal())
        assertEquals(1, crossroad.roadsMap[Direction.SOUTH]!!.awaitingCount)

        crossroad.runStep()
        assertEquals(0, crossroad.carsTotal())
    }

    @Test
    fun `verify car direction transitions`() {
        val tests = listOf(
            Triple(Direction.NORTH, CarDirection.FORWARD, Direction.SOUTH),
            Triple(Direction.EAST, CarDirection.RIGHT, Direction.NORTH),
            Triple(Direction.SOUTH, CarDirection.LEFT, Direction.WEST),
            Triple(Direction.WEST, CarDirection.FORWARD, Direction.EAST)
        )

        tests.forEach { (fromDir, carDir, expectedDestination) ->
            crossroad = Crossroad() // Reset for each test
            val car = Car("test-${fromDir}-${carDir}", carDir)

            crossroad.addCar(car, fromDir)

            assertEquals(
                1,
                crossroad.roadsMap[expectedDestination]!!.awaitingCount,
                "Car from $fromDir going $carDir should await at $expectedDestination"
            )
        }
    }
}