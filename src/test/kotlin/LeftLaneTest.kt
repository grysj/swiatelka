package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

class LeftLaneTest {
    private lateinit var leftLane: LeftLane

    @BeforeEach
    fun setup() {
        leftLane = LeftLane()
    }

    @Test
    fun `car with FORWARD direction should drive away on FR light`() {
        // Arrange
        val car = Car("car1", CarDirection.FORWARD)
        leftLane.addCar(car)

        // Act
        leftLane.step(LightType.FR, 1)

        // Assert
        assertEquals(0, leftLane.countCars())
    }

    @Test
    fun `car with LEFT direction should drive away on LEFT light`() {
        // Arrange
        val car = Car("car1", CarDirection.LEFT)
        leftLane.addCar(car)

        // Act
        leftLane.step(LightType.LEFT, 1)

        // Assert
        assertEquals(0, leftLane.countCars())
    }

    @Test
    fun `car with LEFT direction should not drive away on FR light`() {
        // Arrange
        val car = Car("car1", CarDirection.LEFT)
        val laneChangeListener = LaneChangeListener(leftLane)
        car.addListener(laneChangeListener)
        leftLane.addCar(car)

        // Act
        leftLane.step(LightType.FR, 1)

        // Assert
        assertEquals(1, leftLane.countCars())
    }

    @Test
    fun `car with FORWARD direction should not drive away on LEFT light`() {
        // Arrange
        val car = Car("car1", CarDirection.FORWARD)
        val laneChangeListener = LaneChangeListener(leftLane)
        car.addListener(laneChangeListener)
        leftLane.addCar(car)

        // Act
        leftLane.step(LightType.LEFT, 1)

        // Assert
        assertEquals(1, leftLane.countCars())
    }

    @Test
    fun `should process multiple cars within time limit`() {
        val car1 = Car("car1", CarDirection.FORWARD)
        val car2 = Car("car2", CarDirection.FORWARD)
        val laneChangeListener = LaneChangeListener(leftLane)
        car1.addListener(laneChangeListener)
        car2.addListener(laneChangeListener)
        leftLane.addCar(car1)
        leftLane.addCar(car2)

        leftLane.step(LightType.FR, 2)

        assertEquals(0, leftLane.countCars())
    }

    @Test
    fun `should respect time limit when processing cars`() {
        val car1 = Car("car1", CarDirection.FORWARD)
        val car2 = Car("car2", CarDirection.FORWARD)
        leftLane.addCar(car1)
        leftLane.addCar(car2)

        leftLane.step(LightType.FR, 1)

        assertEquals(1, leftLane.countCars())
    }

    @Test
    fun `first car blocking should prevent subsequent cars from driving away`() {
        val car1 = Car("car1", CarDirection.LEFT)    // This car can't move on FR
        val car2 = Car("car2", CarDirection.FORWARD) // This car could move on FR, but is blocked
        leftLane.addCar(car1)
        leftLane.addCar(car2)

        leftLane.step(LightType.FR, 2)

        assertEquals(2, leftLane.countCars())
    }
}