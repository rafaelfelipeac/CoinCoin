package br.com.rafaelfelipeac.coincoin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.rafaelfelipeac.coincoin.R

import br.com.rafaelfelipeac.coincoin.model.Goal

class CardViewCalculatedGoalsAdapter(private val goals: List<Goal>) : RecyclerView.Adapter<CardViewCalculatedGoalsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_calculated_goals, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val goal: Goal = goals[position]

        holder.name.text = goal.name
        holder.value.text = goal.value.toString()
    }

    override fun getItemCount(): Int {
        return goals.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val name = mView.findViewById<TextView>(R.id.goalName)
        val value = mView.findViewById<TextView>(R.id.goalValue)
    }
}
