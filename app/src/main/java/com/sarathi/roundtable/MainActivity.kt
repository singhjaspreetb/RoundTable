package com.sarathi.roundtable

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    private lateinit var mapsFragment: MapsFragment
    private lateinit var scrl: ScrollingVIewFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        addFragment(HomeFragment.newInstance())
        bottomNavigation.show(0)
        bottomNavigation.add(MeowBottomNavigation.Model(0,R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_explore))
        bottomNavigation.add(MeowBottomNavigation.Model(2, android.R.drawable.ic_media_play))
        bottomNavigation.add(MeowBottomNavigation.Model(3, android.R.drawable.ic_menu_mapmode))
        bottomNavigation.add(MeowBottomNavigation.Model(4,R.drawable.ic_user))

        mapsFragment = MapsFragment()
        scrl= ScrollingVIewFragment()
        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replaceFragment(HomeFragment.newInstance())
                }
                1 -> {
                    replaceFragment(ExploreFragment.newInstance())
                }
                2 -> {
                    replaceFragment(ViewFragment.newInstance())
                }
                3 -> {
                    replaceFragment(mapsFragment)
                }
                4 -> {
                    replaceFragment(UserFragment.newInstance())
                }
                else -> {
                    replaceFragment(HomeFragment.newInstance())
                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun replaceFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //Log.d(MainActivity::class.java.simpleName, "onRequestPermissionsResult: $requestCode ${grantResults[0]}")
        if (requestCode == 101){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                mapsFragment.enableMyLocation()
            }
        }
    }

}