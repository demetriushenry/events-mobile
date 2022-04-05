package com.demetrius.testevents.model

data class Event(
    val id: Int?,
    val title: String?,
    val price: Float?,
    val latitude: Double?,
    val longitude: Double?,
    val image: String?,
    val description: String?,
    val date: Long?,
    val people: List<People>?
)