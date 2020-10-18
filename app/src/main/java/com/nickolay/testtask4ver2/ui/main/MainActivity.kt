package com.nickolay.testtask4ver2.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.nickolay.testtask4ver2.R
import com.nickolay.testtask4ver2.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask4ver2.ui.adapters.SpecialtiesPageAdapter
import com.nickolay.testtask4ver2.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class MainActivity : BaseActivity<List<SpecialtyModel>>() {

    private val isLandscape: Boolean by lazy {
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun renderData(data: List<SpecialtyModel>) {
        view_pager.adapter =
            SpecialtiesPageAdapter(data, supportFragmentManager)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this

        tabs.setupWithViewPager(view_pager)
    }


    fun hide() {
        //топорное решение хорошение решение требует переделать класс модел
        if (!isLandscape) {
            view_pager.visibility = View.GONE
            fContainer.visibility = View.VISIBLE
        }
    }


    override fun onBackPressed() {
        if (fContainer.visibility == View.GONE) {
            super.onBackPressed()
        }

        if (isLandscape) {
            super.onBackPressed()
        } else {
            view_pager.visibility = View.VISIBLE
            fContainer.visibility = View.GONE
        }
    }


    companion object {
        lateinit var instance: MainActivity
            private set
    }

}