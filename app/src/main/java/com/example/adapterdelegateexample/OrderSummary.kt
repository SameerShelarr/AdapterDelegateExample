package com.example.adapterdelegateexample

data class OrderSummary(
    val ticketPrice: Double = 0.0,
    val totalTax: Double = 0.0,
    val convenienceCharges: Double = 0.0,
) : IBookingDetails {
    val totalPayment get() = ticketPrice + totalTax + convenienceCharges
}
