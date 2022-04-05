package com.demetrius.testevents.service

import com.demetrius.testevents.model.Event
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @GET("events")
    @Headers("Accept:application/json", "Content-Type:application/json")
    suspend fun getEvents(): Response<List<Event>>

    @GET("events/{id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    suspend fun getEvent(@Path("id") id: Int): Response<Event>
}