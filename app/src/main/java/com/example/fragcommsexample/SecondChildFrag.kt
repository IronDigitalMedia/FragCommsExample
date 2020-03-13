package com.example.fragcommsexample

import android.content.Context
import android.net.Uri
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
import kotlinx.android.synthetic.main.fragment_first_child.*
import kotlinx.android.synthetic.main.fragment_parent.*
import kotlinx.android.synthetic.main.fragment_second_child.*
import kotlin.random.Random

class SecondChildFrag : Fragment(){

    val TAG:String = "Second"
    lateinit var adapter: Adapter
    lateinit var recyclerView: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    var randomNumStr: String? = null
    var secondList = mutableListOf<Item>()
    private val model: RandomNumberComms by activityViewModels()

    companion object {
        fun newInstance(): SecondChildFrag {
            val fragment = SecondChildFrag()
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_child, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        model.getSecondList().observe(viewLifecycleOwner, Observer<MutableList<Item>> {

            Log.i(TAG,"-$it")

            adapter.updateItems(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        frag_parent_layout_second.setOnClickListener {
            val randoNum = Random
            randomNumStr = randoNum.nextInt().toString()
            val item = Item(randomNumStr!!)
            secondList.add(item)
            sendRandomSecond(secondList)
        }

    }

    fun sendRandomSecond(list: MutableList<Item>) {
        model!!.secondChildNum.postValue(list)
    }

    private fun setupRecyclerView() {
        recyclerView = second_rv
        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.hasFixedSize()
        adapter = Adapter()
        recyclerView.adapter = adapter
    }
}
