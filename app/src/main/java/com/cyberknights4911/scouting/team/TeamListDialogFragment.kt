package com.cyberknights4911.scouting.team

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.cyberknights4911.scouting.database.Team
import com.cyberknights4911.scouting.databinding.FragmentTeamListDialogBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    TeamListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
@AndroidEntryPoint
class TeamListDialogFragment : BottomSheetDialogFragment() {
    private val teamViewModel: TeamViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTeamListDialogBinding.inflate(inflater, container, false)

        val teamAdapter = TeamAdapter(
            Glide.with(this),
            ::selectTeam
        )

        with (binding.root) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = teamAdapter
        }

        teamViewModel.redTeams.observe(viewLifecycleOwner) {
            it?.let { redTeamList ->
                if (redTeamList.isEmpty()) {
                    // TODO: error?, the teams should be there
                } else {
                    teamAdapter.redTeamList = redTeamList
                }
            }
        }

        teamViewModel.blueTeams.observe(viewLifecycleOwner) {
            it?.let { blueTeamList ->
                if (blueTeamList.isEmpty()) {
                    // TODO: error?, the teams should be there
                } else {
                    teamAdapter.blueTeamList = blueTeamList
                }
            }
        }

        teamViewModel.thumbnailMap.observe(viewLifecycleOwner) {
            it?.let { mediaMap ->
                if (mediaMap.isEmpty()) {
                    // TODO ??????
                } else {
                    teamAdapter.thumbnailMap = mediaMap
                }
            }
        }

        return binding.root
    }

    private fun selectTeam(team: Team) {
        findNavController().navigateUp()
        setFragmentResult(
            "teamSelect",
            bundleOf(
                "teamId" to team.tba_key,
                "matchId" to teamViewModel.matchId
            )
        )
    }
}
