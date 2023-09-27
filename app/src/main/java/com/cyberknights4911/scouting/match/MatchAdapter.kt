package com.cyberknights4911.scouting.match

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.cyberknights4911.scouting.databinding.MatchItemBinding

/**
 * [RecyclerView.Adapter] that can display a [MatchWithTeams].
 */
class MatchAdapter(
    val clickListener: MatchListener
) : ListAdapter<MatchWithTeams, MatchAdapter.ViewHolder>(MatchDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MatchItemBinding.inflate(
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
        private val binding: MatchItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(matchWithTeams: MatchWithTeams) {
            binding.root.setOnClickListener {
                clickListener.onClick(matchWithTeams)
            }
            binding.matchNumber.text = "${matchWithTeams.compLevel} ${matchWithTeams.matchNumber}"
            binding.matchBlueAlliance.text = matchWithTeams.blueTeams.map {
                it.teamNumber
            }.joinToString(", ")
            binding.matchRedAlliance.text = matchWithTeams.redTeams.map {
                it.teamNumber
            }.joinToString(", ")
        }
    }

}