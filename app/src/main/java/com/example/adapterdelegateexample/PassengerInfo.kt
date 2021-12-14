package com.example.adapterdelegateexample

data class PassengerInfo(
    val passengers: List<Passenger>
) : IBookingDetails
