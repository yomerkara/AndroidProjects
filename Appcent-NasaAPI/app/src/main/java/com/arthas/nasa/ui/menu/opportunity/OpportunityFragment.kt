package com.arthas.nasa.ui.menu.opportunity

import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.arthas.nasa.R
import com.arthas.nasa.databinding.FragmentOpportunityBinding
import com.arthas.nasa.ui.base.BaseFragment
import com.arthas.nasa.ui.base.VMState
import com.arthas.nasa.ui.menu.adapter.Adapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get


class OpportunityFragment : BaseFragment<OpportunityViewModel, FragmentOpportunityBinding>() {

    lateinit var adapter: Adapter

    override val viewModel: OpportunityViewModel = get()

    override val layoutRes: Int
        get() = R.layout.fragment_opportunity


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.camera_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun initUI(view: View) {
        binding?.rvImages?.layoutManager = GridLayoutManager(context, 2)
        adapter = Adapter()
        binding?.tvCam?.text = "ALL"
        binding?.rvImages?.adapter = adapter
        fetchOpportunityImages("")
    }


    override fun initListener() {
        binding?.cvTitleHolder?.setOnClickListener {
            val popup = PopupMenu(this.activity, binding?.cvTitleHolder)
            popup.menuInflater.inflate(R.menu.camera_menu, popup.menu)

            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.fhaz -> {
                        binding?.tvCam?.text = "FHAZ"
                        fetchOpportunityImages("fhaz")
                    }
                    R.id.rhaz -> {
                        binding?.tvCam?.text = "RHAZ"
                        fetchOpportunityImages("rhaz")
                    }
                    R.id.mast -> {
                        binding?.tvCam?.text = "MAST"
                        fetchOpportunityImages("mast")
                    }
                    R.id.chemcam -> {
                        binding?.tvCam?.text = "CHEMCAM"
                        fetchOpportunityImages("chemcam")
                    }
                    R.id.mahli -> {
                        binding?.tvCam?.text = "MAHLI"
                        fetchOpportunityImages("mahli")
                    }
                    R.id.mardi -> {
                        binding?.tvCam?.text = "MARDI"
                        fetchOpportunityImages("mardi")
                    }
                    R.id.navcam -> {
                        binding?.tvCam?.text = "NAVCAM"
                        fetchOpportunityImages("navcam")
                    }
                    R.id.pancam -> {
                        binding?.tvCam?.text = "PANCAM"
                        fetchOpportunityImages("pancam")
                    }
                    R.id.minites -> {
                        binding?.tvCam?.text = "MINITES"
                        fetchOpportunityImages("minites")
                    }
                    R.id.sminites -> {
                        binding?.tvCam?.text = "ALL"
                        fetchOpportunityImages("")
                    }
                }
                true

            }

            popup.show()
        }
    }

    override fun onChangedScreenState(state: VMState) {

    }

    private fun fetchOpportunityImages(cam: String) {
        lifecycleScope.launch {
            viewModel.fetchCuriosityImages(cam).distinctUntilChanged().collectLatest {
                adapter.addLoadStateListener { loadState ->
                    if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && adapter.itemCount < 1) {
                        binding?.cardView?.visibility = View.VISIBLE
                    } else {
                        binding?.cardView?.visibility = View.GONE
                    }
                }
                adapter.submitData(it)

            }
        }
    }

}


