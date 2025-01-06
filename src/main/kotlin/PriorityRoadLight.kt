package org.example

class PriorityRoadLight : LightSignal() {

    override fun nextSignal(): Unit {
        when (state) {
            LightType.RED -> state = LightType.LEFT
            LightType.FR -> state = LightType.RED
            LightType.LEFT -> state = LightType.FR
        }
    }
}