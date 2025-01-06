package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

class PriorityRoadLightTest {
   private lateinit var light: PriorityRoadLight

   @BeforeEach
   fun setup() {
       light = PriorityRoadLight()
   }

   @Test
   fun `initial state should be FR`() {
       // LightSignal's default state is FR
       assertEquals(LightType.FR, light.state)
   }

   @Test
   fun `nextSignal should change FR to RED`() {
       // Arrange - light starts in FR state

       // Act
       light.nextSignal()

       // Assert
       assertEquals(LightType.RED, light.state)
   }

   @Test
   fun `nextSignal should change RED to LEFT`() {
       // Arrange
       light.state = LightType.RED

       // Act
       light.nextSignal()

       // Assert
       assertEquals(LightType.LEFT, light.state)
   }

   @Test
   fun `nextSignal should change LEFT to FR`() {
       // Arrange
       light.state = LightType.LEFT

       // Act
       light.nextSignal()

       // Assert
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