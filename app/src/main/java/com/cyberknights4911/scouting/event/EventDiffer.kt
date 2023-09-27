package com.cyberknights4911.scouting.event

import androidx.recyclerview.widget.DiffUtil
import com.cyberknights4911.scouting.database.Event

object EventDiffer : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.tba_key == newItem.tba_key
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.name == newItem.name
            && oldItem.city == newItem.city
            && oldItem.startDate == newItem.startDate
    }
}
