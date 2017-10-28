package br.com.rafaelfelipeac.coincoin.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
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


/**
 * A simple [Fragment] subclass.
 */
class FragmentGoals : Fragment(), RecyclerViewClickPosition {

    lateinit var recyclerView: RecyclerView
    lateinit var goals: List<Goal>
    var goalDAO: GoalDAO? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_goals, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewGoals)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        loadGoalsList()

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        loadGoalsList()
        super.onResume()
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

    fun loadGoalsList() {
        goalDAO = GoalDAO(context)
        goals = goalDAO!!.Read()

        val adapter = CardViewGoalAdapter(goals, this)
        recyclerView.adapter = adapter
    }

    override fun getRecyclerViewAdapterPosition(position: Int) {
        val intent: Intent = Intent(context, GoalFormActivity::class.java)
        intent.putExtra("goal", goals.get(position))
        startActivity(intent)
    }
}
