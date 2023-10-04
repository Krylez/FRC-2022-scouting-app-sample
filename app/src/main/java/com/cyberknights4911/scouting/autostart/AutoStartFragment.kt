package com.cyberknights4911.scouting.autostart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.cyberknights4911.scouting.GamePiece
import com.cyberknights4911.scouting.R
import com.cyberknights4911.scouting.databinding.FragmentAutoStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AutoStartFragment : Fragment() {

    private val autoStartViewModel: AutoStartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAutoStartBinding = FragmentAutoStartBinding.inflate(
            inflater, container, false)

        with (binding) {
            root.visibility = View.INVISIBLE

            Glide.with(this@AutoStartFragment).load(R.raw.start_field).into(field)

            startPosition1.setOnClickListener(::startPositionClick)
            startPosition2.setOnClickListener(::startPositionClick)
            startPosition3.setOnClickListener(::startPositionClick)
            startPosition4.setOnClickListener(::startPositionClick)

            startPieceSelector.setOnCheckedChangeListener { _, id ->
                autoStartViewModel.startPiece.postValue(
                    when (id) {
                        startPieceCone.id -> GamePiece.CONE
                        startPieceCube.id -> GamePiece.CUBE
                        else -> GamePiece.NONE
                    }
                )
            }

            autoStartDone.setOnClickListener {
                autoStartViewModel.saveReport()
                val reportId = autoStartViewModel.initialReport.value?.reportId
                if (reportId != null) {
                    findNavController().navigate(
                        AutoStartFragmentDirections.autoStartToAutoReport(
                            reportId = reportId
                        )
                    )
                }  else {
                    Log.e("AutoStartFragment", "No initial reportId found")
                }
            }
        }
        autoStartViewModel.initialReport.observe(viewLifecycleOwner) { initialReport ->
            Log.d("AutoStartFragment", "initial report created")
            binding.root.visibility = View.VISIBLE
        }
        autoStartViewModel.startPosition.observe(viewLifecycleOwner) { position ->
            setStartPositionTextFor(binding, position)
        }
        autoStartViewModel.startPiece.observe(viewLifecycleOwner) { piece ->
            when (piece) {
                GamePiece.CONE -> binding.startPieceSelector.check(binding.startPieceCone.id)
                GamePiece.CUBE -> binding.startPieceSelector.check(binding.startPieceCube.id)
                else -> binding.startPieceSelector.clearCheck()
            }
        }

        return binding.root
    }

    private fun startPositionClick(view: View) {
        autoStartViewModel.startPosition.postValue(
            when (view.id) {
                R.id.startPosition1 -> AutoStartPosition.ONE
                R.id.startPosition2 -> AutoStartPosition.TWO
                R.id.startPosition3 -> AutoStartPosition.THREE
                R.id.startPosition4 -> AutoStartPosition.FOUR
                else -> AutoStartPosition.NONE
            }
        )
    }

    private fun setStartPositionTextFor(
        binding: FragmentAutoStartBinding,
        position: AutoStartPosition
    ) {
        binding.startPositionText.text = when (position) {
            AutoStartPosition.ONE -> getString(R.string.button_start_position_one)
            AutoStartPosition.TWO ->getString(R.string.button_start_position_two)
            AutoStartPosition.THREE -> getString(R.string.button_start_position_three)
            AutoStartPosition.FOUR -> getString(R.string.button_start_position_four)
            else -> ""
        }
    }
}