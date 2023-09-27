package com.cyberknights4911.scouting.event

import com.cyberknights4911.scouting.database.Event

class EventListener(val clickListener: (event: Event) -> Unit) {
    fun onClick(event: Event) = clickListener(event)
}
