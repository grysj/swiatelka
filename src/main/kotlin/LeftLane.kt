package org.example

class LeftLane : Lane() {
    override fun step(light: LightType, time: Int) {
        var remainingTime = time

        while (remainingTime > 0 && cars.isNotEmpty()) {
            val frontCar = cars.first()

            val canDriveAway = when (light) {
                LightType.FR -> frontCar.direction == CarDirection.FORWARD
                LightType.LEFT -> frontCar.direction == CarDirection.LEFT
                else -> false
            }

            if (canDriveAway) {
                frontCar.driveAway()
                remainingTime--
            } else {
                break
            }
        }
    }
}