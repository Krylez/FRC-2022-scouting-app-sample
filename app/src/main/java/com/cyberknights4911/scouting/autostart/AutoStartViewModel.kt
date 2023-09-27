package com.cyberknights4911.scouting.autostart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.cyberknights4911.scouting.GamePiece
import com.cyberknights4911.scouting.database.Report
import com.cyberknights4911.scouting.database.ScoutDatabaseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutoStartViewModel @Inject constructor(
    private val databaseDao: ScoutDatabaseDao,
    state: SavedStateHandle
) : ViewModel() {
    private val matchId = state.get<String>("matchId")!!
    private val teamId = state.get<String>("teamId")!!

    val initialReport: LiveData<Report> = liveData {
        val report = Report()
        report.reportId = databaseDao.insertReport(Report())
        emit(report)
    }

    val startPosition = MutableLiveData<AutoStartPosition>()

    val startPiece = MutableLiveData<GamePiece>()

    fun saveReport() {
        initialReport.value?.let { report ->
            report.startPosition = startPosition.value?.name.toString()
            report.startPiece = startPiece.value?.name.toString()
            report.matchId = matchId
            report.teamId = teamId
            viewModelScope.launch {
                databaseDao.insertReport(report)
            }
        }
    }
}
