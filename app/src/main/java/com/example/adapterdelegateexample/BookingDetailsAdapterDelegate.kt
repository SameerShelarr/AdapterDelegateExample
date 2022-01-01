package com.example.adapterdelegateexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adapterdelegateexample.databinding.BookingInfoLayoutBinding


class BookingInfoAdapterDelegate(
    private val viewType: Int
) {

    fun getViewType() = viewType

    fun isForViewType(items: List<IBookingDetails>, position: Int) =
        items[position] is BookingInfo

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingDetailsAdapter.BookingDetailsViewHolder(
            BookingInfoLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    fun onBindViewHolder(items: List<IBookingDetails>, position: Int, holder: RecyclerView.ViewHolder) {
        val current: BookingInfo = items[position] as BookingInfo
        (holder as BookingDetailsAdapter.BookingDetailsViewHolder).bind(current)
    }
}