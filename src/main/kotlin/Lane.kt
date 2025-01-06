package org.example


abstract class Lane {

    val cars = ArrayDeque<Car>()

    abstract fun step(light: LightType, time: Int): Unit

    fun addCar(car: Car) {
        car.addListener(LaneChangeListener(this))
        cars.add(car)
    }

    fun removeFirst() {
        cars.removeFirst()
    }

    fun countCars(): Int {
        return cars.size
    }
}


