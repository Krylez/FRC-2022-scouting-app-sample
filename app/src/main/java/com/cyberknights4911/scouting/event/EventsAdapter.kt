package com.cyberknights4911.scouting.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cyberknights4911.scouting.database.Event
import com.cyberknights4911.scouting.databinding.EventItemBinding

/**
 * [ListAdapter] that can display a [Event].
 */
class EventsAdapter(
    val clickListener: EventListener
) : ListAdapter<Event, EventsAdapter.ViewHolder>(EventDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EventItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: EventItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {
            binding.root.setOnClickListener {
                clickListener.onClick(event)
            }
            binding.eventName.text = event.name
            binding.eventLocation.text = event.city
            binding.eventDate.text = event.startDate
        }
    }
}
