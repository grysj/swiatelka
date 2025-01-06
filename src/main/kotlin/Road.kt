package org.example

import kotlin.random.Random

class Road {

    val leftLane: Lane = LeftLane()
    val rightLane: Lane = RightLane()
    val rand = Random.Default
    var lightSignal: LightSignal = NonPriorityRoadLight()
    var awaitingCount: Int = 0

    fun subtractCount() {
        awaitingCount--
    }

    fun addCount() {
        awaitingCount++
    }

    fun addCar(car: Car) {
        when (car.direction) {
            CarDirection.FORWARD -> {
                val check = rand.nextFloat()
                if (check >= 0.3) {
                    rightLane.addCar(car)
                } else {
                    leftLane.addCar(car)
                }
            }

            CarDirection.LEFT -> leftLane.addCar(car)
            CarDirection.RIGHT -> rightLane.addCar(car)
        }
    }

    fun step() {
        val time = rand.nextInt(2, 5)
        leftLane.step(lightSignal.state, (time * 1.5).toInt())
        rightLane.step(lightSignal.state, time)
    }

    fun setPriority() {
        lightSignal = PriorityRoadLight()
    }


    fun getCarsCount(): Int {
        return leftLane.countCars() + rightLane.countCars()
    }

}
