package com.arthas.lefrango.ui.menu.details

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.arthas.lefrango.databinding.FragmentDetailsBinding
import com.arthas.lefrango.ui.base.BaseFragment
import com.arthas.lefrango.ui.base.VMState
import org.koin.android.ext.android.get


class DetailsFragment : BaseFragment<DetailsViewModel, FragmentDetailsBinding>() {

    override val viewModel: DetailsViewModel = get()

    override val layoutRes: Int
        get() = com.arthas.lefrango.R.layout.fragment_details


    override fun initUI(view: View) {
        if (viewModel.sharedPref.isVictim == true) {
            binding?.isVictim?.root?.visibility = View.VISIBLE
            binding?.isNotVictim?.root?.visibility = View.GONE
            val myAdapter = this.activity?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_list_item_1,
                    resources.getStringArray(com.arthas.lefrango.R.array.countrys)
                )
            }
            myAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding?.isVictim?.spinner?.adapter = myAdapter

        } else {
            binding?.isNotVictim?.root?.visibility = View.VISIBLE
            binding?.isVictim?.root?.visibility = View.GONE
        }
    }


    override fun initListener() {
        binding?.isVictim?.containerSearch?.setOnClickListener{
            binding?.isVictim?.spinner?.onItemSelectedListener = object :
                OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    pos: Int,
                    id: Long
                ) {
                    val item = parent?.getItemAtPosition(pos)
                    viewModel.country.value = item.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
            viewModel.search()
        }
    }

    override fun onChangedScreenState(state: VMState) {
        when (state) {
            is DetailsVMState.OnSearch -> {
                binding?.isVictim?.spinner?.onItemSelectedListener = object :
                    OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View,
                        pos: Int,
                        id: Long
                    ) {
                        val item = parent?.getItemAtPosition(pos)
                        viewModel.country.value = item.toString()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
                viewModel.search()
            }
        }
    }

}


