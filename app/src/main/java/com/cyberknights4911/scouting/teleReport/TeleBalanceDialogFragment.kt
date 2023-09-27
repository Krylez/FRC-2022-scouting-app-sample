package com.cyberknights4911.scouting.teleReport

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cyberknights4911.scouting.Balance
import com.cyberknights4911.scouting.databinding.FragmentAutoBalanceBinding
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
class TeleBalanceDialogFragment : BottomSheetDialogFragment() {
    private val teleBalanceViewModel: TeleBalanceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAutoBalanceBinding.inflate(inflater, container, false)

        with (binding) {
            root.visibility = View.INVISIBLE
            balance.setOnCheckedChangeListener { _, id ->
                teleBalanceViewModel.balance.postValue(
                    when (id) {
                        engaged.id -> Balance.ENGAGED
                        docked.id -> Balance.DOCKED
                        parked.id -> Balance.PARKED
                        else -> Balance.NONE
                    }
                )
            }

            balanceCancel.setOnClickListener {
                findNavController().navigateUp()
            }
            balanceOkay.setOnClickListener {
                teleBalanceViewModel.saveReport()
                findNavController().navigateUp()
            }
        }

        teleBalanceViewModel.initialReport.observe(viewLifecycleOwner) { initialReport ->
            Log.d("AutoBalanceDialogFragment", "report fetched ${initialReport.reportId}")
            binding.root.visibility = View.VISIBLE
        }

        teleBalanceViewModel.balance.observe(viewLifecycleOwner) { balance ->
            when (balance) {
                Balance.ENGAGED -> {
                    binding.balance.check(binding.engaged.id)
                    binding.balanceOkay.isEnabled = true
                }
                Balance.DOCKED -> {
                    binding.balance.check(binding.docked.id)
                    binding.balanceOkay.isEnabled = true
                }
                Balance.PARKED -> {
                    binding.balance.check(binding.parked.id)
                    binding.balanceOkay.isEnabled = true
                }
                else -> {
                    binding.balance.clearCheck()
                    binding.balanceOkay.isEnabled = false
                }
            }
        }

        return binding.root
    }
}
