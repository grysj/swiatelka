package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PriorityRoadLightTest {
    private lateinit var light: PriorityRoadLight

    @BeforeEach
    fun setup() {
        light = PriorityRoadLight()
    }

    @Test
    fun `initial state should be FR`() {
        assertEquals(LightType.FR, light.state)
    }

    @Test
    fun `nextSignal should change FR to RED`() {

        light.nextSignal()

        assertEquals(LightType.RED, light.state)
    }

    @Test
    fun `nextSignal should change RED to LEFT`() {
        light.state = LightType.RED

        light.nextSignal()

        assertEquals(LightType.LEFT, light.state)
    }

    @Test
    fun `nextSignal should change LEFT to FR`() {
        light.state = LightType.LEFT
        light.nextSignal()

        assertEquals(LightType.FR, light.state)
    }

    @Test
    fun `full cycle should work correctly`() {
        assertEquals(LightType.FR, light.state)

        light.nextSignal()
        assertEquals(LightType.RED, light.state)

        light.nextSignal()
        assertEquals(LightType.LEFT, light.state)

        light.nextSignal()
        assertEquals(LightType.FR, light.state)
    }
}