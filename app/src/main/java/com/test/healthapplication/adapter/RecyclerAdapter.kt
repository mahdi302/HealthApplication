package com.test.healthapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.healthapplication.R
import kotlinx.android.synthetic.main.item_subtitle_main.view.*

class RecyclerAdapter(val items: List<String>, val listener: (String) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_subtitle_main, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: String, listener: (String) -> Unit) = with(itemView) {
            itemView.txt_right_side.text = item
            itemView.txt_left_side.visibility = View.INVISIBLE
            itemView.img_arrow.visibility=View.INVISIBLE

            setOnClickListener { listener(item) }
        }
    }
}