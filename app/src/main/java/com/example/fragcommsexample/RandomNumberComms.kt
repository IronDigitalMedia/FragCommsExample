package com.example.fragcommsexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class RandomNumberComms : ViewModel() {

    val firstChildNum: MutableLiveData<MutableList<Item>> by lazy {
        MutableLiveData<MutableList<Item>>()
    }
    val secondChildNum: MutableLiveData<MutableList<Item>> by lazy {
        MutableLiveData<MutableList<Item>>()
    }

    fun getFirstList(): LiveData<MutableList<Item>>{
        return firstChildNum
    }

    fun getSecondList(): LiveData<MutableList<Item>>{
        return secondChildNum
    }
}