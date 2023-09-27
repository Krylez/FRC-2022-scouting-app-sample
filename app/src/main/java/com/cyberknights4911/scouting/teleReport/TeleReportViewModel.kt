package com.cyberknights4911.scouting.teleReport

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberknights4911.scouting.GamePiece
import com.cyberknights4911.scouting.ScorePosition
import com.cyberknights4911.scouting.database.Report
import com.cyberknights4911.scouting.database.ScoutDatabaseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeleReportViewModel @Inject constructor(
    private val databaseDao: ScoutDatabaseDao,
    state: SavedStateHandle
) : ViewModel() {
    private val reportId = state.get<Long>("reportId")!!

    val initialReport: LiveData<Report> = databaseDao.getReport(reportId)

    val gamePiece = MutableLiveData(GamePiece.NONE)

    val cubeCount = MutableLiveData(0)

    val coneCount = MutableLiveData(0)

    val dropCount = MutableLiveData(0)

    val scorePosition = MutableLiveData(ScorePosition.NONE)

    val highPieces = MutableLiveData(0)

    val midPieces = MutableLiveData(0)

    val lowPieces = MutableLiveData(0)

    val totalScore = MediatorLiveData(0).let { mediator ->
        var highCount = 0
        var midCount = 0
        var lowCount = 0
        mediator.addSource(highPieces) {
            highCount = it
            mediator.setValue(highCount * 6 + midCount * 4 + lowCount * 3)
        }
        mediator.addSource(midPieces) {
            midCount = it
            mediator.setValue(highCount * 6 + midCount * 4 + lowCount * 3)
        }
        mediator.addSource(lowPieces) {
            lowCount = it
            mediator.setValue(highCount * 6 + midCount * 4 + lowCount * 3)
        }
    }

    fun saveReport() {
        initialReport.value?.let { initialReport ->

            initialReport.collectedConesTele = coneCount.value ?: 0
            initialReport.collectedCubesTele = cubeCount.value ?: 0
            initialReport.droppedPiecesTele = dropCount.value ?: 0

            initialReport.scoreHighTele = highPieces.value ?: 0
            initialReport.scoreMidTele = midPieces.value ?: 0
            initialReport.scoreLowTele = lowPieces.value ?: 0

            viewModelScope.launch {
                databaseDao.insertReport(initialReport)
            }
        }
    }

}
