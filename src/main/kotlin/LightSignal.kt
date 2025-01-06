package org.example

abstract class LightSignal {

    var state: LightType = LightType.FR

    abstract fun nextSignal(): Unit
}


