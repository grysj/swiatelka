package org.example

class NonPriorityRoadLight : LightSignal() {


    init {
        state = LightType.RED
    }

    override fun nextSignal(): Unit {
        when (state) {
            LightType.RED -> state = LightType.FR
            LightType.FR -> state = LightType.LEFT
            LightType.LEFT -> state = LightType.RED
        }
    }
}