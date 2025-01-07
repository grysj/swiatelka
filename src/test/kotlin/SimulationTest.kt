package org.example

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class SimulationTest {
    private lateinit var crossroad: Crossroad
    private lateinit var commands: Commands
    private lateinit var simulation: Simulation

    @BeforeEach
    fun setup() {
        crossroad = Crossroad()
        commands = Commands()
        simulation = Simulation(crossroad, commands)
    }

    @Test
    fun `test simulation with single car`() {
        val command = SimulationCommand("Car1", Direction.NORTH, Direction.EAST)
        commands.addCommand(0, command)

        simulation.runSimulation()

        val results = simulation.simulationResults.getResult()
        assertTrue(results.any { (_, cars) -> cars.any { it == "Car1" } })
    }

    @Test
    fun `test simulation with multiple cars on different steps`() {
        commands.addCommand(0, SimulationCommand("Car1", Direction.NORTH, Direction.EAST))
        commands.addCommand(1, SimulationCommand("Car2", Direction.EAST, Direction.WEST))

        simulation.runSimulation()

        val results = simulation.simulationResults.getResult()
        val allCars = results.values.flatten()
        assertTrue(allCars.contains("Car1"))
        assertTrue(allCars.contains("Car2"))
    }

    @Test
    fun `test no cars added when no commands for current step`() {

        commands.addCommand(1, SimulationCommand("Car1", Direction.NORTH, Direction.EAST))


        simulation.runSimulation()

        assertTrue(simulation.simulationResults.getResult().isEmpty())
    }
}

