package com.cyberknights4911.scouting.autoReport

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberknights4911.scouting.Balance
import com.cyberknights4911.scouting.database.Report
import com.cyberknights4911.scouting.database.ScoutDatabaseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AutoBalanceViewModel @Inject constructor(
    private val databaseDao: ScoutDatabaseDao,
    state: SavedStateHandle
) : ViewModel() {
    private val reportId = state.get<Long>("reportId")!!

    val initialReport: LiveData<Report> = databaseDao.getReport(reportId)

    val balance = MutableLiveData(Balance.NONE)

    fun saveReport() {
        initialReport.value?.let { initialReport ->
            initialReport.balanceAuto = balance.value.toString()

            viewModelScope.launch {
                databaseDao.insertReport(initialReport)
            }
        }
    }
}