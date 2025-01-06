package org.example

import com.fasterxml.jackson.annotation.JsonProperty

data class SimulationCommand(
    @JsonProperty("carName")
    val carName: String,
    @JsonProperty("directionFrom")
    val directionFrom: Direction,
    @JsonProperty("directionTo")
    val directionTo: Direction,
)
