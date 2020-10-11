package com.nickolay.testtask4ver2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nickolay.testtask4ver2.R
import com.nickolay.testtask4ver2.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask4ver2.ui.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_employees.*
import kotlinx.android.synthetic.main.fragment_detail_employees.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DetailEmployeesFragment: BaseFragment<List<String>>() {

    override val viewModel: DetailFragmentViewModel by lazy {
        ViewModelProvider(this).get(DetailFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detail_employees, container, false)

        val data = arguments?.getParcelable<EmployeesModel>(EXTRA_DATA)
        data?.apply {
            viewModel.id = id
        }
        data?.apply {
            root.tvFName.text = f_name
            root.tvLName.text = l_name
            root.tvBirthday.text = birthday
            if (avatr_url.isNotBlank()) {
                Picasso.get()
                    .load(avatr_url)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.ic_non_image_24)
                    .fit()
                    .into(root.ivEmployees)
            }
        }
        return root
    }

    override fun renderData(data: List<String>) {

        tvSpecialties.text =
            data.toString()
                .removePrefix("[")
                .removeSuffix("]")
    }


    companion object {
        private val EXTRA_DATA = DetailEmployeesFragment::class.java.name + "extra.DATA"

        @JvmStatic
        fun newInstance(employeeData: EmployeesModel) =
            DetailEmployeesFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_DATA, employeeData)
                }
            }

    }



}