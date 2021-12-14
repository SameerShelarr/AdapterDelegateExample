package com.example.adapterdelegateexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapterdelegateexample.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookingDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookingDetails: List<IBookingDetails> = getDummyData()
        adapter = BookingDetailsAdapter(bookingDetails)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
        }
    }

    private fun getDummyData(): List<IBookingDetails> {
        val bookingInfo = BookingInfo(
            "00001",
            BookingStatus.COMPLETE,
            1,
            BookingClass.ECONOMY,
        )

        val flightInfo = FlightInfo(
            "Mumbai",
            "Delhi",
            2.5,
            "Airbus A380",
            Calendar.getInstance().time,
            Calendar.getInstance().time,
        )

        val passengerInfo = PassengerInfo(
            listOf(
                Passenger(
                    "Sameer Shelar",
                    "23786737",
                )
            )
        )

        val orderSummery = OrderSummary(
            2500.0,
            346.0,
            100.0,
        )

        val refundAndExchange = RefundAndExchange(
            "Refund and exchange",
            "The refund will be processed within 10 working days.",
        )

        return listOf(bookingInfo, flightInfo, passengerInfo, orderSummery, refundAndExchange)
    }
}