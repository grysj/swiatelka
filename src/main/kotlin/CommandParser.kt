package org.example

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

data class CommandFile(
    @JsonProperty("commands")
    val commands: Map<Int, List<SimulationCommand>>
)


class CommandParser {
    private val mapper = ObjectMapper().registerKotlinModule()

    fun parseCommandFile(jsonContent: String): Commands {
        val commandFile = mapper.readValue<CommandFile>(jsonContent)
        val commands = Commands()

        commandFile.commands.forEach { (step, commandList) ->
            commandList.forEach { cmdData ->
                val command = SimulationCommand(
                    cmdData.carName,
                    cmdData.directionFrom,
                    cmdData.directionTo
                )
                commands.addCommand(step, command)
            }
        }

        return commands
    }
}