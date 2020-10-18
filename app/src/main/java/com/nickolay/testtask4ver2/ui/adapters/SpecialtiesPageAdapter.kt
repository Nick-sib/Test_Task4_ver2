package com.nickolay.testtask4ver2.ui.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nickolay.testtask4ver2.data.roomdb.specialty.SpecialtyModel
import com.nickolay.testtask4ver2.ui.fragments.EmployeesFragment


class SpecialtiesPageAdapter(
    private val specialties: List<SpecialtyModel>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) =
        EmployeesFragment.newInstance(specialties[position].specialtyId)

    override fun getPageTitle(position: Int) = specialties[position].specialtyName

    override fun getCount() = specialties.size
}