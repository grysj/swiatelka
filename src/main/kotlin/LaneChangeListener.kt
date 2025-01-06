package org.example

class LaneChangeListener(val lane: Lane) : CarListener {
    override fun onAction() {
        lane.removeFirst()
    }
}