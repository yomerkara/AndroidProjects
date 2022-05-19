package com.arthas.nasa.ui.menu.spirit

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.arthas.nasa.data.model.images.ImageResponse
import com.arthas.nasa.data.model.photos.Photos
import com.arthas.nasa.data.repo.CuriosityRepo
import com.arthas.nasa.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SpiritViewModel(val repository: CuriosityRepo = CuriosityRepo.getInstance()) :
    BaseViewModel() {

    val pagingConfig: PagingConfig = getDefaultPageConfig()
    fun fetchSpiritImages(cam: String): Flow<PagingData<Photos>> {
        return repository.letCuriosityImagesFlow(pagingConfig = pagingConfig, cam, "Spirit")
            .map { it.map { it } }
            .cachedIn(viewModelScope)

    }

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = CuriosityRepo.DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

}