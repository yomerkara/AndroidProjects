package com.arthas.nasa.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.arthas.nasa.data.model.photos.Photos
import com.arthas.nasa.module.RemoteInjector
import com.arthas.nasa.service.api.CuriosityApiService
import com.arthas.nasa.ui.menu.adapter.ImagePagingSource
import kotlinx.coroutines.flow.Flow

class CuriosityRepo(val curiosityApiService: CuriosityApiService = RemoteInjector.injectCuriosityApiService()) {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 35

        fun getInstance() = CuriosityRepo()
    }

    fun letCuriosityImagesFlow(
        pagingConfig: PagingConfig = getDefaultPageConfig(),
        cam: String,
        type: String
    ): Flow<PagingData<Photos>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { ImagePagingSource(curiosityApiService, cam, type) }
        ).flow
    }

    fun letCuriosityImagesLiveData(
        pagingConfig: PagingConfig = getDefaultPageConfig(),
        cam: String,
        type: String
    ): LiveData<PagingData<Photos>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { ImagePagingSource(curiosityApiService, cam, type) }
        ).liveData
    }

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}