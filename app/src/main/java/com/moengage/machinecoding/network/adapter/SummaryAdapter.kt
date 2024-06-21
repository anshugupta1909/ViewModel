package com.moengage.machinecoding.network.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moengage.machinecoding.R
import com.moengage.machinecoding.network.model.QuestionAnswer

class SummaryAdapter (private val items: List<QuestionAnswer>) : RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder>() {

    class SummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
        val answerTextView: TextView = itemView.findViewById(R.id.answerTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_summary, parent, false)
        return SummaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        val item = items[position]
        holder.questionTextView.text = item.question
        holder.answerTextView.text = item.answer
    }

    override fun getItemCount(): Int = items.size
}