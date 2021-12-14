package com.example.adapterdelegateexample

import java.util.*

data class FlightInfo(
    val from: String,
    val to: String,
    val flightDuration: Double,
    val airplaneModel: String,
    val departureDateTime: Date,
    val arrivalDateTime: Date,
) : IBookingDetails
