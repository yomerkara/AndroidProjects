package com.arthas.lefrango.ui.menu.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arthas.lefrango.core.SharedPref
import com.arthas.lefrango.data.repo.LoginRepo
import com.arthas.lefrango.service.util.AppResult
import com.arthas.lefrango.ui.base.BaseViewModel
import com.arthas.lefrango.util.listener.OnSingleClickListener
import kotlinx.coroutines.launch

class DetailsViewModel(val sharedPref: SharedPref, val loginRepo: LoginRepo) : BaseViewModel() {

    val country = MutableLiveData<String>()
    val doctorOneName = MutableLiveData<String>()
    val doctorTwoName = MutableLiveData<String>()
    val doctorThreeName = MutableLiveData<String>()

    val onSearch = OnSingleClickListener {
        state.value = DetailsVMState.OnSearch()
    }

    fun search() {
        viewModelScope.launch {
            when (val result = country.value?.let { loginRepo.getDoctors(it) }) {
                is AppResult.Success -> {
                    doctorOneName.value =
                        "${result.successData.data.doctor[0].name}+${result.successData.data.doctor[0].surname}"

                    doctorTwoName.value =
                        "${result.successData.data.doctor[1].name}+${result.successData.data.doctor[1].surname}"

                    doctorThreeName.value =
                        "${result.successData.data.doctor[2].name}+${result.successData.data.doctor[2].surname}"
                }
                is AppResult.Error -> {

                }
            }
        }
    }
}