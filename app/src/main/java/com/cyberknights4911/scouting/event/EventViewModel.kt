package com.cyberknights4911.scouting.event

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberknights4911.scouting.BlueAllianceService
import com.cyberknights4911.scouting.database.Event
import com.cyberknights4911.scouting.database.ScoutDatabaseDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val blueAllianceService: BlueAllianceService,
    private val databaseDao: ScoutDatabaseDao
) : ViewModel() {
    init {
        fetchEvents()
    }

    val events : LiveData<List<Event>> = databaseDao.getAllEvents()

    val eventsFlow : Flow<List<Event>> = databaseDao.getAllEventsFlow()

    private fun fetchEvents() {
        blueAllianceService.districteventList("2023pnw")
            .enqueue(object: Callback<List<EventJson>> {
                override fun onResponse(call: Call<List<EventJson>>, response: Response<List<EventJson>>) {
                    val eventJsonList = response.body()
                    if (eventJsonList == null) {
                        Log.e("EventViewModel", "No response!")
                    } else {
                        viewModelScope.launch {
                            Log.d("EventViewModel", "Inserting ${eventJsonList.size} events into database")
                            databaseDao.insertEvents(
                                eventJsonList.map {
                                    Event(
                                        tba_key = it.key,
                                        name = it.name,
                                        city = it.city,
                                        startDate = it.start_date
                                    )
                                }
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<List<EventJson>>, t: Throwable) {
                    Log.e("EventViewModel", "Failure", t)
                }
            })
    }
}
