package br.com.rafaelfelipeac.coincoin.fragments


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.LinearLayout

import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.activities.GoalFormActivity
import br.com.rafaelfelipeac.coincoin.adapter.CardViewGoalAdapter
import br.com.rafaelfelipeac.coincoin.dao.GoalDAO
import br.com.rafaelfelipeac.coincoin.interfaces.RecyclerViewClickPosition
import br.com.rafaelfelipeac.coincoin.model.Goal
import kotlinx.android.synthetic.main.fragment_b.*


/**
 * A simple [Fragment] subclass.
 */
class FragmentB : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var goals: List<Goal>
    var goalDAO: GoalDAO? = null
    var viewFrag: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_b, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewGoals)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        goalDAO = GoalDAO(context)
        goals = goalDAO!!.Read()

        val adapter = CardViewGoalAdapter(goals)
        recyclerView.adapter = adapter

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.addgoal, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu_addGoal -> {
                val intent = Intent(activity, GoalFormActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
