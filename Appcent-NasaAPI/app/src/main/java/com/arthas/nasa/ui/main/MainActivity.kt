package com.arthas.nasa.ui.main

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.arthas.nasa.R
import com.arthas.nasa.databinding.ActivityMainBinding
import com.arthas.nasa.ui.base.BaseActivity
import com.arthas.nasa.ui.base.VMState
import org.koin.android.ext.android.get

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var navController: NavController

    override val layoutRes: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel = get()

    override fun initUI() {
        navController = findNavController(R.id.nav_host_fragment)
        binding?.navView?.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.menu_curiosity -> showNavView()
                R.id.menu_opportunity -> showNavView()
                R.id.menu_spirit -> showNavView()
                else -> hideNavView()
            }
        }

        setupActionBarWithNavController(navController)
    }

    override fun initListener() {

    }

    override fun onChangedScreenState(state: VMState) {

    }

    private fun showNavView() {
        binding?.navView?.visibility = View.VISIBLE

    }

    private fun hideNavView() {
        binding?.navView?.visibility = View.GONE

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navController.navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun open(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }

}