package com.cyberknights4911.scouting.autoReport

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cyberknights4911.scouting.GamePiece
import com.cyberknights4911.scouting.ScorePosition
import com.cyberknights4911.scouting.databinding.FragmentAutoReportBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AutoReportFragment : Fragment() {

    private val autoReportViewModel: AutoReportViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAutoReportBinding = FragmentAutoReportBinding.inflate(
            inflater, container, false)

        with (binding) {
            root.visibility = View.INVISIBLE

            gamePiece.setOnCheckedChangeListener { _, id ->
                autoReportViewModel.gamePiece.postValue(
                    when (id) {
                        gamePieceCone.id -> GamePiece.CONE
                        gamePieceCube.id -> GamePiece.CUBE
                        else -> GamePiece.NONE
                    }
                )
            }

            collectButton.setOnClickListener {
                when (autoReportViewModel.gamePiece.value) {
                    GamePiece.CONE -> {
                        autoReportViewModel.coneCount.postValue(
                            autoReportViewModel.coneCount.value?.plus(1) ?: 1
                        )
                    }
                    GamePiece.CUBE -> {
                        autoReportViewModel.cubeCount.postValue(
                            autoReportViewModel.cubeCount.value?.plus(1) ?: 1
                        )
                    }
                    else -> {
                        Log.d("AutoReportFragment", "collect clicked without piece")
                    }
                }
                autoReportViewModel.gamePiece.postValue(GamePiece.NONE)
            }
            dropButton.setOnClickListener {
                autoReportViewModel.gamePiece.postValue(GamePiece.NONE)
                autoReportViewModel.dropCount.postValue(
                    autoReportViewModel.dropCount.value?.plus(1) ?: 1
                )
            }

            scoreLevel.setOnCheckedChangeListener { _, id ->
                autoReportViewModel.scorePosition.postValue(
                    when (id) {
                        scoreHigh.id -> ScorePosition.HIGH
                        scoreMid.id -> ScorePosition.MID
                        scoreLow.id -> ScorePosition.LOW
                        else -> ScorePosition.NONE
                    }
                )
            }

            scoreButton.setOnClickListener {
                when(autoReportViewModel.scorePosition.value) {
                    ScorePosition.HIGH -> {
                        autoReportViewModel.highPieces.postValue(
                            autoReportViewModel.highPieces.value?.plus(1) ?: 1
                        )
                    }
                    ScorePosition.MID -> {
                        autoReportViewModel.midPieces.postValue(
                            autoReportViewModel.midPieces.value?.plus(1) ?: 1
                        )
                    }
                    ScorePosition.LOW -> {
                        autoReportViewModel.lowPieces.postValue(
                            autoReportViewModel.lowPieces.value?.plus(1) ?: 1
                        )
                    }
                    else -> {
                        Log.d("AutoReportFragment", "score clicked without piece")
                    }
                }
                autoReportViewModel.scorePosition.postValue(ScorePosition.NONE)
            }

            balanceButton.setOnClickListener {
                val reportId = autoReportViewModel.initialReport.value?.reportId
                if (reportId != null) {
                    findNavController().navigate(
                        AutoReportFragmentDirections.autoReportFragmentToAutoBalanceDialogFragment(
                            reportId
                        )
                    )
                } else {
                    Log.e("AutoReportFragment", "No initial reportId found")
                }
            }

            reportDone.setOnClickListener {
                autoReportViewModel.saveReport()
                val reportId = autoReportViewModel.initialReport.value?.reportId
                if (reportId != null) {
                    findNavController().navigate(
                        AutoReportFragmentDirections.autoReportFragmentToTeleReportFragment(
                            reportId
                        )
                    )
                } else {
                    Log.e("AutoReportFragment", "No initial reportId found")
                }
            }
        }

        autoReportViewModel.initialReport.observe(viewLifecycleOwner) { initialReport ->
            Log.d("AutoReportFragment", "report fetched ${initialReport.reportId}")
            binding.root.visibility = View.VISIBLE
        }

        autoReportViewModel.gamePiece.observe(viewLifecycleOwner) { piece ->
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

        autoReportViewModel.cubeCount.observe(viewLifecycleOwner) { count ->
            binding.collectedCubeCount.text = count.toString()
        }

        autoReportViewModel.coneCount.observe(viewLifecycleOwner) { count ->
            binding.collectedConeCount.text = count.toString()
        }

        autoReportViewModel.scorePosition.observe(viewLifecycleOwner) { position ->
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

        autoReportViewModel.highPieces.observe(viewLifecycleOwner) { count ->
            binding.scoreCountHigh.text = count.toString()
        }

        autoReportViewModel.midPieces.observe(viewLifecycleOwner) { count ->
            binding.scoreCountMid.text = count.toString()
        }

        autoReportViewModel.lowPieces.observe(viewLifecycleOwner) { count ->
            binding.scoreCountLow.text = count.toString()
        }

        return binding.root
    }
}
