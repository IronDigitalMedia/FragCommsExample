package com.example.fragcommsexample

import android.app.Application
import androidx.lifecycle.MutableLiveData

class ItemRepository {

    private var items = mutableListOf<Item>()
    private var mutableLiveData = MutableLiveData<MutableList<Item>>()
    private var application = Application()

    fun ItemRepository(application: Application){
        this.application = application
    }

    /*fun getMutableLiveData(): MutableLiveData<MutableList<Item>>{

    }*/
}