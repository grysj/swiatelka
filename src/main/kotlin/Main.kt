package org.example

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Usage: program <input_json_path> <output_path>")
        return
    }

    try {
        val jsonContent = java.io.File(args[0]).readText()
        val parser = CommandParser()
        val commands = parser.parseCommandFile(jsonContent)

        val crossroad = Crossroad()
        val simulation = Simulation(crossroad, commands)
        simulation.runSimulation()

        val results = simulation.simulationResults.getResult()
        val outputFile = java.io.File(args[1])

        val mapper = jacksonObjectMapper()
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, results)

        println("Simulation completed. Results written to ${args[1]}")

    } catch (e: Exception) {
        println("Error: ${e.message}")
        e.printStackTrace()
    }
}