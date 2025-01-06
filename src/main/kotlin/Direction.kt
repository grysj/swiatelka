package org.example

enum class Direction {
    NORTH, SOUTH, EAST, WEST;

    private fun left(): Direction {
        return when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }

    private fun right(): Direction {
        return when (this) {
            NORTH -> WEST
            EAST -> NORTH
            SOUTH -> EAST
            WEST -> SOUTH
        }
    }

    private fun forward(): Direction {
        return when (this) {
            NORTH -> SOUTH
            EAST -> WEST
            SOUTH -> NORTH
            WEST -> EAST
        }
    }


    fun getDestination(carDirection: CarDirection): Direction {
        return when (carDirection) {
            CarDirection.FORWARD -> this.forward()
            CarDirection.RIGHT -> this.right()
            CarDirection.LEFT -> this.left()
        }
    }

    fun calculateCarDirection(directionTo: Direction): CarDirection {
        return when (directionTo) {
            this.forward() -> {
                CarDirection.FORWARD
            }
            this.left() -> {
                CarDirection.LEFT
            }
            else -> {
                CarDirection.RIGHT
            }
        }
    }

}