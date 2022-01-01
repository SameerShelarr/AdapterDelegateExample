package com.example.adapterdelegateexample

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegateexample.databinding.*
import java.text.SimpleDateFormat
import java.util.*

class BookingDetailsAdapter(
    private val bookingDetailsList: List<IBookingDetails>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val bookingInfoAdapterDelegate = BookingInfoAdapterDelegate(BOOKING_INFO_TYPE_INT)
    private val flightInfoAdapterDelegate = FlightInfoAdapterDelegate(FLIGHT_INFO_TYPE_INT)
    private val passengerInfoAdapterDelegate = PassengerInfoAdapterDelegate(PASSENGER_INFO_TYPE_INT)
    private val orderSummaryAdapterDelegate = OrderSummaryAdapterDelegate(ORDER_SUMMARY_TYPE_INT)
    private val refundAndExchangeAdapterDelegate = RefundAndExchangeAdapterDelegate(
        REFUND_AND_EXCHANGE_TYPE_INT
    )

    companion object {
        private const val BOOKING_INFO_TYPE_INT = 0
        private const val FLIGHT_INFO_TYPE_INT = 1
        private const val PASSENGER_INFO_TYPE_INT = 2
        private const val ORDER_SUMMARY_TYPE_INT = 3
        private const val REFUND_AND_EXCHANGE_TYPE_INT = 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            bookingInfoAdapterDelegate.getViewType() -> {
                bookingInfoAdapterDelegate.onCreateViewHolder(parent)
            }
            flightInfoAdapterDelegate.getViewType() -> {
                flightInfoAdapterDelegate.onCreateViewHolder(parent)
            }
            passengerInfoAdapterDelegate.getViewType() -> {
                passengerInfoAdapterDelegate.onCreateViewHolder(parent)
            }
            orderSummaryAdapterDelegate.getViewType() -> {
                orderSummaryAdapterDelegate.onCreateViewHolder(parent)
            }
            refundAndExchangeAdapterDelegate.getViewType() -> {
                refundAndExchangeAdapterDelegate.onCreateViewHolder(parent)
            }
            else -> {
                throw IllegalArgumentException("Cannot create a View no suitable ViewHolder found!")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            bookingInfoAdapterDelegate.isForViewType(bookingDetailsList, position) -> {
                bookingInfoAdapterDelegate.getViewType()
            }
            flightInfoAdapterDelegate.isForViewType(bookingDetailsList, position) -> {
                flightInfoAdapterDelegate.getViewType()
            }
            passengerInfoAdapterDelegate.isForViewType(bookingDetailsList, position) -> {
                passengerInfoAdapterDelegate.getViewType()
            }
            orderSummaryAdapterDelegate.isForViewType(bookingDetailsList, position) -> {
                orderSummaryAdapterDelegate.getViewType()
            }
            refundAndExchangeAdapterDelegate.isForViewType(bookingDetailsList, position) -> {
                refundAndExchangeAdapterDelegate.getViewType()
            }
            else -> {
                throw IllegalArgumentException("Cannot create a View no suitable ViewHolder found!")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            bookingInfoAdapterDelegate.getViewType() -> bookingInfoAdapterDelegate.onBindViewHolder(
                bookingDetailsList,
                position,
                holder
            )
            flightInfoAdapterDelegate.getViewType() -> flightInfoAdapterDelegate.onBindViewHolder(
                bookingDetailsList,
                position,
                holder
            )
            passengerInfoAdapterDelegate.getViewType() -> passengerInfoAdapterDelegate.onBindViewHolder(
                bookingDetailsList,
                position,
                holder
            )
            orderSummaryAdapterDelegate.getViewType() -> orderSummaryAdapterDelegate.onBindViewHolder(
                bookingDetailsList,
                position,
                holder
            )
            refundAndExchangeAdapterDelegate.getViewType() -> refundAndExchangeAdapterDelegate.onBindViewHolder(
                bookingDetailsList,
                position,
                holder
            )
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