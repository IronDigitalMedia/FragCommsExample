package com.example.fragcommsexample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val fragmentsList:ArrayList<Fragment> = arrayListOf()
    val fragmentTitle:ArrayList<String> = arrayListOf()

    override fun getItem(position: Int): Fragment {
        return fragmentsList.get(position)
    }

    override fun getCount(): Int {
        return fragmentsList.size
    }

    fun addFragment(fragment: Fragment, title:String){

        fragmentsList.add(fragment)
        fragmentTitle.add(title)

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle.get(position)
    }
}