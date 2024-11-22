package com.example.newsamania

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newsamania.databinding.ActivityMainBinding
import com.example.newsamania.fragments.BusinessFragment
import com.example.newsamania.fragments.EntertainmentFragment
import com.example.newsamania.fragments.HealthFragment
import com.example.newsamania.fragments.HomeFragment
import com.example.newsamania.fragments.ScienceFragment
import com.example.newsamania.fragments.SearchFragment
import com.example.newsamania.fragments.SportsFragment
import com.example.newsamania.fragments.TechnologyFragment
import com.sun.khobrakhobor.adapter.ViewPagerAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentArrayList = ArrayList<Fragment>()

        fragmentArrayList.add(HomeFragment())
        fragmentArrayList.add(BusinessFragment())
        fragmentArrayList.add(SportsFragment())
        fragmentArrayList.add(HealthFragment())
        fragmentArrayList.add(ScienceFragment())
        fragmentArrayList.add(TechnologyFragment())
        fragmentArrayList.add(EntertainmentFragment())
        fragmentArrayList.add(SearchFragment())

        val adapter = ViewPagerAdapter(this, supportFragmentManager, fragmentArrayList)
        binding.fabSearch.setOnClickListener {
            binding.fragmentcontainer.currentItem = 8
        }

        binding.fragmentcontainer.adapter = adapter
        binding.include.setupWithViewPager(binding.fragmentcontainer)
    }
}