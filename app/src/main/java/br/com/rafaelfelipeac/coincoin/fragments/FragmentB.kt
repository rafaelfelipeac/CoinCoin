package br.com.rafaelfelipeac.coincoin.fragments


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*

import br.com.rafaelfelipeac.coincoin.R
import br.com.rafaelfelipeac.coincoin.activities.GoalFormActivity
import br.com.rafaelfelipeac.coincoin.adapter.CardViewGoalAdapter
import br.com.rafaelfelipeac.coincoin.dao.GoalDAO
import br.com.rafaelfelipeac.coincoin.interfaces.RecyclerViewClickPosition
import br.com.rafaelfelipeac.coincoin.model.Goal


/**
 * A simple [Fragment] subclass.
 */
class FragmentB : Fragment(), RecyclerViewClickPosition {

    companion object {

    }

    lateinit var goals: List<Goal>
    var goalDAO: GoalDAO? = null

    var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    var mLayoutManager: RecyclerView.LayoutManager? = null

    lateinit var list: RecyclerView;

    override fun getRecyclerViewAdapterPosition(position: Int) {
        val goal = goals.get(position)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_b, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)



        goalDAO = GoalDAO(context)
        goals = goalDAO!!.Read()

        mLayoutManager = LinearLayoutManager(context)
        list.layoutManager = mLayoutManager

        loadList()

        super.onCreate(savedInstanceState)
    }

    fun loadList() {
        //mAdapter = CardViewGoalAdapter(goals)
        list.adapter = mAdapter
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

}// Required empty public constructor
