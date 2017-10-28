package br.com.rafaelfelipeac.coincoin.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.interfaces.RecyclerViewClickPosition
import br.com.rafaelfelipeac.coincoin.model.Goal

/**
 * Created by Rafael Felipe on 19/10/2017.
 */

class CardViewGoalAdapter(val goals: List<Goal>, val positionInterface: RecyclerViewClickPosition) : RecyclerView.Adapter<CardViewGoalAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val goal: Goal = goals[position]

        holder.name.text = goal.name
        holder.value.text = "R$ " + "%.2f".format(goal.value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val itemLayoutView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_goal, null)
        return ViewHolder(itemLayoutView, positionInterface)
    }

    override fun getItemCount(): Int {
        return goals.size
    }

    class ViewHolder(itemLayoutView: View, positionInterface: RecyclerViewClickPosition) : RecyclerView.ViewHolder(itemLayoutView), View.OnClickListener {
        lateinit var pI: RecyclerViewClickPosition

        init {
            itemLayoutView.setOnClickListener(this)
            pI = positionInterface
        }

        override fun onClick(p0: View?) {
            Log.d("tome", "tome")
            pI.getRecyclerViewAdapterPosition(this.layoutPosition)
        }

        val name = itemLayoutView.findViewById<TextView>(R.id.goalName)
        val value = itemLayoutView.findViewById<TextView>(R.id.goalValue)
    }


}