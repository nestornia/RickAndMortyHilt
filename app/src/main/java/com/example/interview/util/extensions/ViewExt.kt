package com.example.interview.util.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

fun View.show(show: Boolean = true) {
    visibility = if (show) View.VISIBLE else View.GONE
}
val ViewGroup.inflater
    get() = LayoutInflater.from(context)

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int) -> Unit) = also {
    itemView.setOnClickListener { event.invoke(adapterPosition) }
}