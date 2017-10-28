package br.com.rafaelfelipeac.coincoin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.model.Goal

class CardViewCalculatedGoalsAdapter(private val goals: List<Goal>, private val price: Double?) : RecyclerView.Adapter<CardViewCalculatedGoalsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val goal: Goal = goals[position]

        holder.name.text = goal.name
        holder.value.text = "R$ " + "%.2f".format(goal.value)
        holder.percent.text = "%.2f".format(goal.calculation(price!!)) + "%"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_calculated_goals, null)
        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int {
        return goals.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.goalCalculatedName)
        val value = view.findViewById<TextView>(R.id.goalCalculatedValue)
        val percent = view.findViewById<TextView>(R.id.goalCalculatedPercent)
    }
}
