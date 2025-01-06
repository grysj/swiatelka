package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DirectionTest {

   @Test
   fun `getDestination for FORWARD movement`() {
       assertEquals(Direction.SOUTH, Direction.NORTH.getDestination(CarDirection.FORWARD))
       assertEquals(Direction.WEST, Direction.EAST.getDestination(CarDirection.FORWARD))
       assertEquals(Direction.NORTH, Direction.SOUTH.getDestination(CarDirection.FORWARD))
       assertEquals(Direction.EAST, Direction.WEST.getDestination(CarDirection.FORWARD))
   }

   @Test
   fun `getDestination for RIGHT turns`() {
       assertEquals(Direction.WEST, Direction.NORTH.getDestination(CarDirection.RIGHT))
       assertEquals(Direction.NORTH, Direction.EAST.getDestination(CarDirection.RIGHT))
       assertEquals(Direction.EAST, Direction.SOUTH.getDestination(CarDirection.RIGHT))
       assertEquals(Direction.SOUTH, Direction.WEST.getDestination(CarDirection.RIGHT))
   }

   @Test
   fun `getDestination for LEFT turns`() {
       assertEquals(Direction.EAST, Direction.NORTH.getDestination(CarDirection.LEFT))
       assertEquals(Direction.SOUTH, Direction.EAST.getDestination(CarDirection.LEFT))
       assertEquals(Direction.WEST, Direction.SOUTH.getDestination(CarDirection.LEFT))
       assertEquals(Direction.NORTH, Direction.WEST.getDestination(CarDirection.LEFT))
   }

   @Test
   fun `verify full rotation works correctly`() {
       var direction = Direction.NORTH

       repeat(4) {
           direction = direction.getDestination(CarDirection.LEFT)
       }
       assertEquals(Direction.NORTH, direction)

       direction = Direction.NORTH
       repeat(4) {
           direction = direction.getDestination(CarDirection.RIGHT)
       }
       assertEquals(Direction.NORTH, direction)
   }

}