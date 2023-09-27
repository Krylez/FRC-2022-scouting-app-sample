package com.cyberknights4911.scouting.teleReport

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cyberknights4911.scouting.GamePiece
import com.cyberknights4911.scouting.R
import com.cyberknights4911.scouting.ScorePosition
import com.cyberknights4911.scouting.databinding.FragmentTeleReportBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeleReportFragment : Fragment() {

    private val teleReportViewModel: TeleReportViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTeleReportBinding = FragmentTeleReportBinding.inflate(
            inflater, container, false)

        with (binding) {
            root.visibility = View.INVISIBLE

            gamePiece.setOnCheckedChangeListener { _, id ->
                teleReportViewModel.gamePiece.postValue(
                    when (id) {
                        gamePieceCone.id -> GamePiece.CONE
                        gamePieceCube.id -> GamePiece.CUBE
                        else -> GamePiece.NONE
                    }
                )
            }

            collectButton.setOnClickListener {
                when (teleReportViewModel.gamePiece.value) {
                    GamePiece.CONE -> {
                        teleReportViewModel.coneCount.postValue(
                            teleReportViewModel.coneCount.value?.plus(1) ?: 1
                        )
                    }
                    GamePiece.CUBE -> {
                        teleReportViewModel.cubeCount.postValue(
                            teleReportViewModel.cubeCount.value?.plus(1) ?: 1
                        )
                    }
                    else -> {
                        Log.d("TeleReportFragment", "collect clicked without piece")
                    }
                }
                teleReportViewModel.gamePiece.postValue(GamePiece.NONE)
            }
            dropButton.setOnClickListener {
                teleReportViewModel.gamePiece.postValue(GamePiece.NONE)
                teleReportViewModel.dropCount.postValue(
                    teleReportViewModel.dropCount.value?.plus(1) ?: 1
                )
            }

            scoreLevel.setOnCheckedChangeListener { _, id ->
                teleReportViewModel.scorePosition.postValue(
                    when (id) {
                        scoreHigh.id -> ScorePosition.HIGH
                        scoreMid.id -> ScorePosition.MID
                        scoreLow.id -> ScorePosition.LOW
                        else -> ScorePosition.NONE
                    }
                )
            }

            scoreButton.setOnClickListener {
                when(teleReportViewModel.scorePosition.value) {
                    ScorePosition.HIGH -> {
                        teleReportViewModel.highPieces.postValue(
                            teleReportViewModel.highPieces.value?.plus(1) ?: 1
                        )
                    }
                    ScorePosition.MID -> {
                        teleReportViewModel.midPieces.postValue(
                            teleReportViewModel.midPieces.value?.plus(1) ?: 1
                        )
                    }
                    ScorePosition.LOW -> {
                        teleReportViewModel.lowPieces.postValue(
                            teleReportViewModel.lowPieces.value?.plus(1) ?: 1
                        )
                    }
                    else -> {
                        Log.d("TeleReportFragment", "score clicked without piece")
                    }
                }
                teleReportViewModel.scorePosition.postValue(ScorePosition.NONE)
            }

            balanceButton.setOnClickListener {
                val reportId = teleReportViewModel.initialReport.value?.reportId
                if (reportId != null) {
                    findNavController().navigate(
                        TeleReportFragmentDirections.teleReportFragmentToTeleBalanceDialogFragment(
                            reportId
                        )
                    )
                } else {
                    Log.e("TeleReportFragment", "No initial reportId found")
                }
            }

            reportDone.setOnClickListener {
                teleReportViewModel.saveReport()
                val reportId = teleReportViewModel.initialReport.value?.reportId
                if (reportId != null) {
                    findNavController().popBackStack(R.id.matchFragment, false)
                } else {
                    Log.e("TeleReportFragment", "No initial reportId found")
                }
            }
        }

        teleReportViewModel.initialReport.observe(viewLifecycleOwner) { initialReport ->
            Log.d("TeleReportFragment", "report fetched ${initialReport.reportId}")
            binding.root.visibility = View.VISIBLE
        }

        teleReportViewModel.gamePiece.observe(viewLifecycleOwner) { piece ->
            when (piece) {
                GamePiece.CONE -> {
                    binding.gamePiece.check(binding.gamePieceCone.id)
                    binding.collectButton.isEnabled = true
                    binding.dropButton.isEnabled = true
                }
                GamePiece.CUBE -> {
                    binding.gamePiece.check(binding.gamePieceCube.id)
                    binding.collectButton.isEnabled = true
                    binding.dropButton.isEnabled = true
                }
                else -> {
                    binding.gamePiece.clearCheck()
                    binding.collectButton.isEnabled = false
                    binding.dropButton.isEnabled = false
                }
            }
        }

        teleReportViewModel.cubeCount.observe(viewLifecycleOwner) { count ->
            binding.collectedCubeCount.text = count.toString()
        }

        teleReportViewModel.coneCount.observe(viewLifecycleOwner) { count ->
            binding.collectedConeCount.text = count.toString()
        }

        teleReportViewModel.scorePosition.observe(viewLifecycleOwner) { position ->
            when (position) {
                ScorePosition.HIGH -> {
                    binding.scoreLevel.check(binding.scoreHigh.id)
                    binding.scoreButton.isEnabled = true
                }
                ScorePosition.MID -> {
                    binding.scoreLevel.check(binding.scoreMid.id)
                    binding.scoreButton.isEnabled = true
                }
                ScorePosition.LOW -> {
                    binding.scoreLevel.check(binding.scoreLow.id)
                    binding.scoreButton.isEnabled = true
                }
                else -> {
                    binding.scoreLevel.clearCheck()
                    binding.scoreButton.isEnabled = false
                }
            }
        }

        teleReportViewModel.highPieces.observe(viewLifecycleOwner) { count ->
            binding.scoreCountHigh.text = count.toString()
        }

        teleReportViewModel.midPieces.observe(viewLifecycleOwner) { count ->
            binding.scoreCountMid.text = count.toString()
        }

        teleReportViewModel.lowPieces.observe(viewLifecycleOwner) { count ->
            binding.scoreCountLow.text = count.toString()
        }

        return binding.root
    }
}
