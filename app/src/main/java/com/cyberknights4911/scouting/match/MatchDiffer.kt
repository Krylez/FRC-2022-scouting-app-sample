package com.cyberknights4911.scouting.match

import androidx.recyclerview.widget.DiffUtil

object MatchDiffer : DiffUtil.ItemCallback<MatchWithTeams>() {
    override fun areItemsTheSame(oldItem: MatchWithTeams, newItem: MatchWithTeams): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: MatchWithTeams, newItem: MatchWithTeams): Boolean {
        return oldItem.compLevel == newItem.compLevel
            && oldItem.matchNumber == newItem.matchNumber
            && oldItem.matchTbaKey == newItem.matchTbaKey
            && oldItem.blueTeams == newItem.blueTeams
            && oldItem.redTeams == newItem.redTeams
    }
}
