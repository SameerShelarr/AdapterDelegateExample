package com.example.adapterdelegateexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegateexample.databinding.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: 14/12/21 Create a separate branch for demoing delegation and conventional way

class BookingDetailsAdapter(
    private val bookingDetailsList: List<IBookingDetails>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val BOOKING_INFO_TYPE_INT = 0
        private const val FLIGHT_INFO_TYPE_INT = 1
        private const val PASSENGER_INFO_TYPE_INT = 2
        private const val ORDER_SUMMARY_TYPE_INT = 3
        private const val REFUND_AND_EXCHANGE_TYPE_INT = 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            0 ->
                BookingDetailsViewHolder(
                    BookingInfoLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            1 ->
                FlightInfoViewHolder(
                    FlightInfoLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            2 ->
                PassengerInfoViewHolder(
                    PassengerInfoLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            3 ->
                OrderSummaryViewHolder(
                    OrderSummaryLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            4 ->
                RefundAndExchangeViewHolder(
                    RefundAndExchangeLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> throw IllegalArgumentException("Cannot create a View no suitable ViewHolder found!")
        }


    override fun getItemViewType(position: Int): Int =
        when (bookingDetailsList[position]) {
            is BookingInfo -> BOOKING_INFO_TYPE_INT
            is FlightInfo -> FLIGHT_INFO_TYPE_INT
            is PassengerInfo -> PASSENGER_INFO_TYPE_INT
            is OrderSummary -> ORDER_SUMMARY_TYPE_INT
            is RefundAndExchange -> REFUND_AND_EXCHANGE_TYPE_INT
            else -> -1
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val currentBookingDetails = bookingDetailsList[position]) {
            is BookingInfo -> (holder as BookingDetailsViewHolder).bind(currentBookingDetails)
            is FlightInfo -> (holder as FlightInfoViewHolder).bind(currentBookingDetails)
            is PassengerInfo -> (holder as PassengerInfoViewHolder).bind(currentBookingDetails)
            is OrderSummary -> (holder as OrderSummaryViewHolder).bind(currentBookingDetails)
            is RefundAndExchange -> (holder as RefundAndExchangeViewHolder).bind(currentBookingDetails)
            else -> throw IllegalArgumentException("Cannot create a View no suitable ViewHolder found!")
        }
    }

    override fun getItemCount(): Int = bookingDetailsList.size

    class BookingDetailsViewHolder(private val binding: BookingInfoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookingInfo: BookingInfo) {
            binding.apply {
                bookingIdText.text = bookingInfo.bookingId
                bookingStatusText.text = bookingInfo.bookingStatus.name
                val passengerAndClassTxt =
                    "${bookingInfo.noOfPassengers} passengers, ${bookingInfo.BookingClass.name} class"
                passengerAndClassText.text = passengerAndClassTxt
            }
        }
    }

    class FlightInfoViewHolder(private val binding: FlightInfoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(flightInfo: FlightInfo) {
            binding.apply {
                fromText.text = flightInfo.from
                fromTitleText.text = flightInfo.from
                toText.text = flightInfo.to
                toTitleText.text = flightInfo.to
                airplaneModelText.text = flightInfo.airplaneModel
                "Flight duration is ${flightInfo.flightDuration} hrs".also {
                    flightDurationText.text = it
                }
                SimpleDateFormat("EEE, d MMM HH:mm Z", Locale.getDefault())
                    .format(flightInfo.departureDateTime).also {
                        departureTimeText.text = it
                    }
                SimpleDateFormat("EEE, d MMM HH:mm Z", Locale.getDefault())
                    .format(flightInfo.arrivalDateTime).also {
                        arrivalTimeText.text = it
                    }
            }
        }
    }

    class PassengerInfoViewHolder(private val binding: PassengerInfoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(passengerInfo: PassengerInfo) {

            val adapter = PassengersAdapter(passengerInfo.passengers)

            binding.apply {
                passengersRecyclerView.setHasFixedSize(true)
                passengersRecyclerView.layoutManager =
                    LinearLayoutManager(passengersRecyclerView.context)
                passengersRecyclerView.adapter = adapter
            }
        }
    }

    class OrderSummaryViewHolder(private val binding: OrderSummaryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orderSummary: OrderSummary) {
            binding.apply {
                "₹${orderSummary.ticketPrice}".also {
                    ticketPriceText.text = it
                }
                "₹${orderSummary.totalTax}".also {
                    taxText.text = it
                }
                "₹${orderSummary.convenienceCharges}".also {
                    convenienceFeeText.text = it
                }
                "₹${orderSummary.totalPayment}".also {
                    totalPaymentText.text = it
                }
            }
        }
    }

    class RefundAndExchangeViewHolder(private val binding: RefundAndExchangeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(refundAndExchange: RefundAndExchange) {
            binding.apply {
                titleText.text = refundAndExchange.title
                description.text = refundAndExchange.description
            }
        }
    }
}