package br.com.rafaelfelipeac.coincoin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.LinearLayout

import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.activities.MainActivity
import br.com.rafaelfelipeac.coincoin.adapter.CardViewCalculatedGoalsAdapter
import br.com.rafaelfelipeac.coincoin.adapter.CardViewGoalAdapter
import br.com.rafaelfelipeac.coincoin.dao.GoalDAO
import br.com.rafaelfelipeac.coincoin.model.Goal

class FragmentCalculatedGoals : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var goals: List<Goal>
    var goalDAO: GoalDAO? = null
    var price: Double? = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_calculated_goals, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewCalculatedGoals)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        goalDAO = GoalDAO(context)
        goals = goalDAO!!.Read()

        price = (activity as MainActivity).getPrice()

        val adapter = CardViewCalculatedGoalsAdapter(goals, price!!)
        recyclerView.adapter = adapter

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            (activity as MainActivity).replacForeMain()
            return true
        }

        return false
    }
}
