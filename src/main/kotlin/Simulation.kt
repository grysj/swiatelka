package org.example

class Simulation(val crossroad: Crossroad, val commands: Commands) {


    var currentStep = 0

    val simulationResults = SimulationResult(this)


    private fun newCar(command: SimulationCommand): Unit {
        val car = Car(command.carName, command.directionFrom.calculateCarDirection(command.directionTo))
        car.addListener(UpdateResultOnExit(simulationResults, car))
        crossroad.addCar(car, command.directionFrom)
    }


    fun runSimulation() {
        for (command in commands.getCommand(currentStep)) {
            newCar(command)
        }

        if (crossroad.carsTotal() == 0 && currentStep == 0) {
            return
        }

        while (crossroad.carsTotal() > 0 || commands.lastCommmandOn >= currentStep) {

            currentStep++
            crossroad.runStep()


            for (command in commands.getCommand(currentStep)) {
                newCar(command)
            }
        }
    }

}