package com.arthas.nasa.ui.menu.curiosity

import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.arthas.nasa.R
import com.arthas.nasa.databinding.FragmentCuriosityBinding
import com.arthas.nasa.ui.base.BaseFragment
import com.arthas.nasa.ui.base.VMState
import com.arthas.nasa.ui.menu.adapter.Adapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get


class CuriosityFragment : BaseFragment<CuriosityViewModel, FragmentCuriosityBinding>() {

    lateinit var adapter: Adapter

    override val viewModel: CuriosityViewModel = get()

    override val layoutRes: Int
        get() = R.layout.fragment_curiosity

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.camera_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun initUI(view: View) {
        binding?.rvImages?.layoutManager = GridLayoutManager(context, 2)
        adapter = Adapter()
        binding?.tvCam?.text = "ALL"
        binding?.rvImages?.adapter = adapter
        fetchCuriosityImages("")
    }

    override fun initListener() {
        binding?.cvTitleHolder?.setOnClickListener {
            val popup = PopupMenu(this.activity, binding?.cvTitleHolder)
            popup.menuInflater.inflate(R.menu.camera_menu, popup.menu)

            popup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.fhaz -> {
                        binding?.tvCam?.text = "FHAZ"
                        fetchCuriosityImages("fhaz")
                    }
                    R.id.rhaz -> {
                        binding?.tvCam?.text = "RHAZ"
                        fetchCuriosityImages("rhaz")
                    }
                    R.id.mast -> {
                        binding?.tvCam?.text = "MAST"
                        fetchCuriosityImages("mast")
                    }
                    R.id.chemcam -> {
                        binding?.tvCam?.text = "CHEMCAM"
                        fetchCuriosityImages("chemcam")
                    }
                    R.id.mahli -> {
                        binding?.tvCam?.text = "MAHLI"
                        fetchCuriosityImages("mahli")
                    }
                    R.id.mardi -> {
                        binding?.tvCam?.text = "MARDI"
                        fetchCuriosityImages("mardi")
                    }
                    R.id.navcam -> {
                        binding?.tvCam?.text = "NAVCAM"
                        fetchCuriosityImages("navcam")
                    }
                    R.id.pancam -> {
                        binding?.tvCam?.text = "PANCAM"
                        fetchCuriosityImages("pancam")
                    }
                    R.id.minites -> {
                        binding?.tvCam?.text = "MINITES"
                        fetchCuriosityImages("minites")
                    }
                    R.id.sminites -> {
                        binding?.tvCam?.text = "ALL"
                        fetchCuriosityImages("")
                    }
                }
                true

            }

            popup.show()
        }
    }

    override fun onChangedScreenState(state: VMState) {

    }

    private fun fetchCuriosityImages(cam: String) {
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
