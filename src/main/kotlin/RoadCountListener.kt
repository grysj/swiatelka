package org.example

class RoadCountListener(val road: Road) : CarListener {
    override fun onAction() {
        road.subtractCount()
    }
}