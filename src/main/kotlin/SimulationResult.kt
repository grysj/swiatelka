package org.example

class SimulationResult(val simulation: Simulation) {


    var result = HashMap<Int, ArrayList<String>>()


    fun updateResult(car: Car) {
        result.getOrPut(simulation.currentStep) { ArrayList() }.add(car.getName())
    }

    fun getResult(): Map<Int, List<String>> {
        return result.toMap()
    }

}