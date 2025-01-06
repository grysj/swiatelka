package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

class RightLaneTest {
    private lateinit var rightLane: RightLane

    @BeforeEach
    fun setup() {
        rightLane = RightLane()
    }

    @Test
    fun `car with FORWARD direction should drive away on FR light`() {
        val car = Car("car1", CarDirection.FORWARD)
        rightLane.addCar(car)

        rightLane.step(LightType.FR, 1)

        assertTrue(rightLane.cars.isEmpty())
    }

    @Test
    fun `car with RIGHT direction should drive away on FR light`() {
        val car = Car("car1", CarDirection.RIGHT)
        rightLane.addCar(car)

        rightLane.step(LightType.FR, 1)

        assertTrue(rightLane.cars.isEmpty())
    }

    @Test
    fun `car with LEFT direction should not drive away on FR light`() {
        val car = Car("car1", CarDirection.LEFT)
        rightLane.addCar(car)

        rightLane.step(LightType.FR, 1)

        assertEquals(1, rightLane.cars.size)
    }

    @Test
    fun `car should not drive away on LEFT light regardless of direction`() {
        val car1 = Car("car1", CarDirection.FORWARD)
        val car2 = Car("car2", CarDirection.RIGHT)
        rightLane.addCar(car1)
        rightLane.addCar(car2)

        rightLane.step(LightType.LEFT, 2)

        assertEquals(2, rightLane.cars.size)
    }

    @Test
    fun `should process multiple eligible cars within time limit`() {

        val car1 = Car("car1", CarDirection.FORWARD)
        val car2 = Car("car2", CarDirection.RIGHT)
        rightLane.addCar(car1)
        rightLane.addCar(car2)

        rightLane.step(LightType.FR, 2)

        assertTrue(rightLane.countCars() == 0)
    }

    @Test
    fun `should respect time limit when processing cars`() {
        val car1 = Car("car1", CarDirection.FORWARD)
        val car2 = Car("car2", CarDirection.RIGHT)
        rightLane.addCar(car1)
        rightLane.addCar(car2)

        rightLane.step(LightType.FR, 1)

        assertEquals(1, rightLane.countCars())
    }

    @Test
    fun `first car blocking should prevent subsequent cars from driving away`() {
        val car1 = Car("car1", CarDirection.LEFT)     // This car can't move on FR
        val car2 = Car("car2", CarDirection.FORWARD)  // This car could move on FR, but is blocked
        rightLane.addCar(car1)
        rightLane.addCar(car2)

        rightLane.step(LightType.FR, 2)

        assertEquals(2, rightLane.countCars())
    }
}