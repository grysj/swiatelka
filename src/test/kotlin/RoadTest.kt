package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoadTest {
    private lateinit var road: Road

    @BeforeEach
    fun setup() {
        road = Road()
    }

    @Test
    fun `initial state should have correct default values`() {
        assertTrue(road.leftLane is LeftLane)
        assertTrue(road.rightLane is RightLane)
        assertTrue(road.lightSignal is NonPriorityRoadLight)
        assertEquals(0, road.awaitingCount)
    }

    @Test
    fun `addCount should increment awaitingCount`() {
        road.addCount()

        assertEquals(1, road.awaitingCount)
    }

    @Test
    fun `subtractCount should decrement awaitingCount`() {
        road.addCount()

        road.subtractCount()

        assertEquals(0, road.awaitingCount)
    }

    @Test
    fun `setPriority should change light signal to PriorityRoadLight`() {
        road.setPriority()

        assertTrue(road.lightSignal is PriorityRoadLight)
    }

    @Test
    fun `LEFT direction car should always go to leftLane`() {
        val car = Car("test1", CarDirection.LEFT)

        road.addCar(car)

        assertEquals(1, road.leftLane.cars.size)
        assertEquals(0, road.rightLane.cars.size)
        assertEquals(car, road.leftLane.cars.first())
    }

    @Test
    fun `RIGHT direction car should always go to rightLane`() {

        val car = Car("test1", CarDirection.RIGHT)

        road.addCar(car)

        assertEquals(0, road.leftLane.cars.size)
        assertEquals(1, road.rightLane.cars.size)
        assertEquals(car, road.rightLane.cars.first())
    }


}