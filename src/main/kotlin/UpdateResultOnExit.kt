package org.example

class UpdateResultOnExit(val simResult: SimulationResult, val car: Car) : CarListener {
    override fun onAction() {
        simResult.updateResult(car)
    }

}