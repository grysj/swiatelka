package org.example

class Car(val carID: String, val direction: CarDirection) {

    var listeners: List<CarListener> = listOf()

    fun addListener(listener: CarListener) {
        listeners += listener
    }

    fun driveAway() {
        for (listener in listeners) {
            listener.onAction()
        }
    }

    fun getName(): String = carID
}
