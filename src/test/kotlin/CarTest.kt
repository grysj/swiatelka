package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CarTest {
    
    @Test
    fun `getName should return correct carID`() {
        val expectedId = "ABC123"
        val car = Car(expectedId, CarDirection.FORWARD)

        val actualId = car.getName()

        assertEquals(expectedId, actualId)
    }

    
    @Test
    fun `getName should preserve special characters in carID`() {
        val expectedId = "Car#123-@!"
        val car = Car(expectedId, CarDirection.FORWARD)

        val actualId = car.getName()

        assertEquals(expectedId, actualId)
    }
}