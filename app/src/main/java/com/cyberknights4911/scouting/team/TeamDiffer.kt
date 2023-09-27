package com.cyberknights4911.scouting.team

import androidx.recyclerview.widget.DiffUtil
import com.cyberknights4911.scouting.database.Team

object TeamDiffer : DiffUtil.ItemCallback<TeamWithLogo>() {
    override fun areItemsTheSame(oldItem: TeamWithLogo, newItem: TeamWithLogo): Boolean {
        return oldItem.team.tba_key == newItem.team.tba_key
    }

    override fun areContentsTheSame(oldItem: TeamWithLogo, newItem: TeamWithLogo): Boolean {
        return oldItem.team.teamNumber == newItem.team.teamNumber
            && oldItem.team.city == newItem.team.city
            && oldItem.team.nickname == newItem.team.nickname
            && oldItem.logo == newItem.logo
    }
}
