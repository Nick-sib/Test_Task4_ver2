package com.nickolay.testtask4ver2.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nickolay.testtask4ver2.R
import com.nickolay.testtask4ver2.data.roomdb.employees.EmployeesModel
import com.nickolay.testtask4ver2.ui.adapters.EmployeesAdapter
import com.nickolay.testtask4ver2.ui.base.BaseFragment
import com.nickolay.testtask4ver2.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_employees.*
import kotlinx.android.synthetic.main.fragment_employees.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class EmployeesFragment: BaseFragment<List<EmployeesModel>>() {


    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Main + Job()
    }

    override val viewModel: EmployeesViewModel by lazy {
        ViewModelProvider(this).get(EmployeesViewModel::class.java)
    }

    override fun renderData(data: List<EmployeesModel>) {
        (recyclerView.adapter as EmployeesAdapter).data = data
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getLong(EXTRA_DATA)
        id?.apply {
            viewModel.id = this
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_employees, container, false)?.apply {

            recyclerView.adapter = EmployeesAdapter{
                parentFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fContainer,
                        DetailEmployeesFragment.newInstance(it)
                    )
                    .commit()
                MainActivity.instance.hide()
            }
        }


    companion object {
        private val EXTRA_DATA = EmployeesFragment::class.java.name + "extra.DATA"

        @JvmStatic
        fun newInstance(specialtyId: Long) =
            EmployeesFragment().apply {
                arguments = Bundle().apply {
                    putLong(EXTRA_DATA, specialtyId)
                }
            }
    }

}