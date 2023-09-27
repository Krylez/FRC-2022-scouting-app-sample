package com.cyberknights4911.scouting.team

import android.util.Base64
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.res.colorResource
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.cyberknights4911.scouting.R
import com.cyberknights4911.scouting.database.Team
import com.cyberknights4911.scouting.databinding.TeamItemDialogBinding
import java.lang.Integer.max

/**
 * [RecyclerView.Adapter] that can display a [Team].
 */
class TeamAdapter(
    private val requestManager: RequestManager,
    private val teamListener: TeamListener
) : ListAdapter<TeamWithLogo, TeamAdapter.ViewHolder>(TeamDiffer) {
    var thumbnailMap: Map<String, String> = mapOf()
        set(value) {
            field = value
            submitMergedList()
        }
    var redTeamList: List<Team> = listOf()
        set(value) {
            field = value
            submitMergedList()
        }

    var blueTeamList: List<Team> = listOf()
        set(value) {
            field = value
            submitMergedList()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TeamItemDialogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }

    private fun submitMergedList() {
        if (redTeamList.isEmpty() || blueTeamList.isEmpty()) {
            return
        }

        val mergedList = mutableListOf<TeamWithLogo>()
        val maxSize = max(redTeamList.size, blueTeamList.size)
        for (index in 0..<maxSize) {
            if (index < redTeamList.size) {
                redTeamList[index].let {
                    mergedList.add(TeamWithLogo(it, thumbnailMap[it.tba_key]))
                }
            } else {
                // add dummy
                mergedList.add(TeamWithLogo(
                    Team("", "dummy", "dummy", "", -1),
                    ""
                ))
            }
            if (index < blueTeamList.size) {
                blueTeamList[index].let {
                    mergedList.add(TeamWithLogo(it, thumbnailMap[it.tba_key]))
                }
            } else {
                // add dummy
                mergedList.add(TeamWithLogo(
                    Team("", "dummy", "dummy", "", -1),
                    ""
                ))
            }
        }
        submitList(mergedList)
    }

    inner class ViewHolder(
        private val binding: TeamItemDialogBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, teamWithLogo: TeamWithLogo) {
            binding.root.setOnClickListener {
                teamListener.onClick(teamWithLogo)
            }
            if (!teamWithLogo.logo.isNullOrEmpty()) {
                requestManager
                    .load(Base64.decode(teamWithLogo.logo, Base64.DEFAULT))
                    .into(binding.logo)
            }
            // even is red, odd is blue
            val color = with (binding.root.context) {
                if (position % 2 == 0) {
                    getColor(R.color.red_alliance)
                } else {
                    getColor(R.color.blue_alliance)
                }
            }
            binding.number.setTextColor(color)
            binding.nickname.setTextColor(color)
            binding.number.text = teamWithLogo.team.teamNumber.toString()
            binding.nickname.text = teamWithLogo.team.nickname
        }
    }
}
