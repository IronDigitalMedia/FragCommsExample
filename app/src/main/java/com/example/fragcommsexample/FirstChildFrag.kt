package com.example.fragcommsexample

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_first_child.*
import kotlinx.android.synthetic.main.fragment_parent.*
import kotlin.random.Random

class FirstChildFrag : Fragment() {

    val TAG:String = "First"
    lateinit var adapter: Adapter
    lateinit var recyclerView: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    var randomNumStr: String? = null
    var firstList = mutableListOf<Item>()
    private val model: RandomNumberComms by activityViewModels()

    companion object {
        fun newInstance(): FirstChildFrag {
            val fragment = FirstChildFrag()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_child, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        model.getFirstList().observe(viewLifecycleOwner, Observer<MutableList<Item>> {

            Log.i(TAG,"-$it")

            adapter.updateItems(it)
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        frag_parent_layout_first.setOnClickListener {
            val randoNum = Random
            randomNumStr = randoNum.nextInt().toString()
            val item = Item(randomNumStr!!)
            firstList.add(item)
            sendRandomFirst(firstList)
        }
    }

    fun sendRandomFirst(list: MutableList<Item>) {
        model.firstChildNum.postValue(list)
    }


    private fun setupRecyclerView() {
        recyclerView = first_rv
        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.hasFixedSize()
        adapter = Adapter()
        recyclerView.adapter = adapter
    }

}
