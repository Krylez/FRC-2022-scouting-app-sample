package com.cyberknights4911.scouting.event

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.cyberknights4911.scouting.R
import com.cyberknights4911.scouting.databinding.FragmentEventsListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of events.
 */
@AndroidEntryPoint
class EventsFragment : Fragment() {
    private val eventViewModel: EventViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentEventsListBinding = FragmentEventsListBinding.inflate(
            inflater, container, false)

        val eventsAdapter = EventsAdapter(
            EventListener {
                Log.d("EventsFragment", "Event selected: " + it.name)

                findNavController().navigate(
                    EventsFragmentDirections.actionEventsFragmentToMatchFragment(it.tba_key)
                )
            }
        )

        with (binding) {
            list.let {
                it.layoutManager = LinearLayoutManager(it.context)
                it.adapter = eventsAdapter
            }
        }

        eventViewModel.events.observe(viewLifecycleOwner) {
            it?.let {
                eventsAdapter.submitList(it)
            }
        }

        return binding.root
    }
}
