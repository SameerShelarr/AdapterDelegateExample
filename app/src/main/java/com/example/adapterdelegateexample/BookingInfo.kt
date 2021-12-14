package com.example.adapterdelegateexample

data class BookingInfo(
    val bookingId: String,
    val bookingStatus: BookingStatus,
    val noOfPassengers: Int,
    val BookingClass: BookingClass,
) : IBookingDetails
