package com.example.fragcommsexample

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.list.customListAdapter
import kotlinx.android.synthetic.main.fragment_parent.*
import org.jetbrains.anko.backgroundColor
import kotlin.random.Random


class ParentFrag : Fragment() {

    val TAG:String = "Parent"
    var randomNumStr: String? = null
    private val model: RandomNumberComms by activityViewModels()
    val first:String = "FIRST"
    val second:String = "SECOND"
    var firstList = mutableListOf<Item>()
    var secondList = mutableListOf<Item>()
    lateinit var adapter: Adapter
    lateinit var recyclerView: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    var items = mutableListOf<Item>()
    var dialog:MaterialDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_parent, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        frag_parent_showList.setOnClickListener {

           showList()

        }

        model.getFirstList().observe(viewLifecycleOwner, Observer {
            Log.i(TAG,"$it")
        })
        model.getSecondList().observe(viewLifecycleOwner, Observer {
            Log.i(TAG,"$it")
        })

        //Log.i(TAG,"${model!!.getFirstList().value!!}")
        //Log.i(TAG,"${model!!.getSecondList().value!!}")

    }

    private fun showList() {

        val tempList1 = model.getFirstList().value!!

        val tempList2 = model.getSecondList().value!!

        tempList1.addAll(tempList2)

        Log.i(TAG,"$firstList")

        setupAdapter()

        adapter.updateItems(tempList1)

        dialog = MaterialDialog(requireContext())
            .title(R.string.show_list)
            .customView(R.layout.list)
            .customListAdapter(adapter = adapter)
            .cornerRadius(10f)


        dialog!!.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFrags()
        setupTabs()

    }

    private fun setupFrags() {
        val first = FirstChildFrag()
        val second = SecondChildFrag()
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.frag_firstchild_frag,first)
        fragmentTransaction.replace(R.id.frag_secondchild_frag,second)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun setupTabs(){
        val fragmentAdapter = TabAdapter(childFragmentManager)

        viewPager.adapter = fragmentAdapter
        fragmentAdapter.addFragment(FirstChildFrag.newInstance(),first)
        fragmentAdapter.addFragment(SecondChildFrag.newInstance(),second)
        viewPager.adapter!!.notifyDataSetChanged()
        tabs.setupWithViewPager(viewPager)

    }

    private fun setupAdapter() {
        adapter = Adapter()
        linearLayoutManager = LinearLayoutManager(requireContext())
    }

}

