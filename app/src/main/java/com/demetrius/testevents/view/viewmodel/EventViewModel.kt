package com.demetrius.testevents.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.demetrius.testevents.model.Event
import com.demetrius.testevents.repository.EventRepository
import com.demetrius.testevents.repository.Result

class EventViewModel(private val repository: EventRepository) : ViewModel() {

    fun getEvents(): LiveData<Result<List<Event>?>> = repository.getEvents()
    fun getEvent(id: Int): LiveData<Result<Event?>> = repository.getEvent(id)
}