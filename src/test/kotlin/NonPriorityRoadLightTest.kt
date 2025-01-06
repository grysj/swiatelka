package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NonPriorityRoadLightTest {
    private lateinit var light: NonPriorityRoadLight

    @BeforeEach
    fun setup() {
        light = NonPriorityRoadLight()
    }

    @Test
    fun `initial state should be RED`() {
        assertEquals(LightType.RED, light.state)
    }

    @Test
    fun `nextSignal should change RED to FR`() {

        light.nextSignal()

        assertEquals(LightType.FR, light.state)
    }

    @Test
    fun `nextSignal should change FR to LEFT`() {

        light.state = LightType.FR

        light.nextSignal()

        assertEquals(LightType.LEFT, light.state)
    }

    @Test
    fun `nextSignal should change LEFT to RED`() {
        light.state = LightType.LEFT

        light.nextSignal()

        assertEquals(LightType.RED, light.state)
    }

    @Test
    fun `full cycle should work correctly`() {
        assertEquals(LightType.RED, light.state)

        light.nextSignal()
        assertEquals(LightType.FR, light.state)

        light.nextSignal()
        assertEquals(LightType.LEFT, light.state)

        light.nextSignal()
        assertEquals(LightType.RED, light.state)
    }
}