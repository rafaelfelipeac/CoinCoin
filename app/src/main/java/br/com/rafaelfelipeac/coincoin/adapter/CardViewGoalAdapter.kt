package br.com.rafaelfelipeac.coincoin.adapter

import android.support.v7.widget.RecyclerView
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

class CardViewGoalAdapter : RecyclerView.Adapter<CardViewGoalAdapter.ViewHolder> {
    var goals: Array<Goal>
    var mPositionInterface : RecyclerViewClickPosition? = null

    var name: TextView? = null
    var value: TextView? = null

    constructor(goals : List<Goal>) {
        this.goals = goals.toTypedArray()
        //this.mPositionInterface = positionInterface
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        name?.text = goals[position].name
        value?.text = goals[position].value.toString()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val itemLayoutView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_goal, null)

        this.name = itemLayoutView.findViewById(R.id.goalName)
        this.value = itemLayoutView.findViewById(R.id.goalValue)


        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int {
        return goals.count()
    }

    inner class ViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

        constructor(itemLayoutView: View) : super(itemLayoutView) {
            itemLayoutView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            mPositionInterface?.getRecyclerViewAdapterPosition(this.layoutPosition)
        }
    }


}