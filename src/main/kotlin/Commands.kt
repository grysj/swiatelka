package org.example

import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.math.max

class Commands {

    @JsonProperty("commands")
    val commands = HashMap<Int, ArrayList<SimulationCommand>>()

    var lastCommmandOn: Int = 0


    fun addCommand(onStep: Int, command: SimulationCommand) {
        lastCommmandOn = max(lastCommmandOn, onStep)
        commands.getOrPut(onStep) { ArrayList() }.add(command)
    }


    fun getCommand(onStep: Int): ArrayList<SimulationCommand> {
        return commands[onStep] ?: ArrayList()
    }
}