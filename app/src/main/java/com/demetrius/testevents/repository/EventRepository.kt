package com.demetrius.testevents.repository

import androidx.lifecycle.liveData
import com.demetrius.testevents.service.ApiService
import java.net.ConnectException

sealed class Result<out R> {
    data class Success<out T>(val data: T?) : Result<T?>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

class EventRepository(private val service: ApiService) {

    fun getEvents() = liveData {
        try {
            val response = service.getEvents()
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Error on retrieve events")))
            }
        } catch (e: ConnectException) {
            emit(Result.Error(exception = Exception("Error on establish connection")))
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun getEvent(id: Int) = liveData {
        try {
            val response = service.getEvent(id)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Error on retrieve this event: $id")))
            }
        } catch (e: ConnectException) {
            emit(Result.Error(exception = Exception("Error on establish connection")))
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }
}