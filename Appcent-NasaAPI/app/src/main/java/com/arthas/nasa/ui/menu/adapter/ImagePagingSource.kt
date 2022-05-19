package com.arthas.nasa.ui.menu.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arthas.nasa.data.model.photos.Photos
import com.arthas.nasa.data.repo.CuriosityRepo.Companion.DEFAULT_PAGE_INDEX
import com.arthas.nasa.service.api.CuriosityApiService
import retrofit2.HttpException
import java.io.IOException

class ImagePagingSource(
    val curiosityApiService: CuriosityApiService,
    val cam: String,
    val type: String
) :
    PagingSource<Int, Photos>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val token = "PIcE6uciy8bCJj2aki3EUtQPlL6aluZgmKVJKUqy"
            when (type) {
                "Curiosity" -> {
                    if (cam.isNotEmpty()) {
                        val response = curiosityApiService.getCuriosityImagesCam(
                            cam,
                            token,
                            page,
                            params.loadSize,
                        )
                        LoadResult.Page(
                            response.photos,
                            prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                            nextKey = if (response.photos.isEmpty()) null else page + 1
                        )
                    } else {
                        val response = curiosityApiService.getCuriosityImages(
                            token,
                            page,
                            params.loadSize
                        )
                        LoadResult.Page(
                            response.photos,
                            prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                            nextKey = if (response.photos.isEmpty()) 1 else page + 1
                        )
                    }
                }
                "Opportunity" -> {
                    if (cam.isNotEmpty()) {
                        val response = curiosityApiService.getOpportunityImagesCam(
                            cam,
                            token,
                            page,
                            params.loadSize,
                        )
                        LoadResult.Page(
                            response.photos,
                            prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                            nextKey = if (response.photos.isEmpty()) null else page + 1
                        )
                    } else {
                        val response = curiosityApiService.getOpportunityImages(
                            token,
                            page,
                            params.loadSize
                        )
                        LoadResult.Page(
                            response.photos,
                            prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                            nextKey = if (response.photos.isEmpty()) 1 else page + 1
                        )
                    }
                }
                "Spirit" -> {
                    if (cam.isNotEmpty()) {
                        val response = curiosityApiService.getSpiritImagesCam(
                            cam,
                            token,
                            page,
                            params.loadSize,
                        )
                        LoadResult.Page(
                            response.photos,
                            prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                            nextKey = if (response.photos.isEmpty()) null else page + 1
                        )
                    } else {
                        val response = curiosityApiService.getSpiritImages(
                            token,
                            page,
                            params.loadSize
                        )
                        LoadResult.Page(
                            response.photos,
                            prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                            nextKey = if (response.photos.isEmpty()) 1 else page + 1
                        )
                    }
                }
                else -> {
                    if (cam.isNotEmpty()) {
                        val response = curiosityApiService.getCuriosityImagesCam(
                            cam,
                            token,
                            page,
                            params.loadSize,
                        )
                        LoadResult.Page(
                            response.photos,
                            prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                            nextKey = if (response.photos.isEmpty()) null else page + 1
                        )
                    } else {
                        val response = curiosityApiService.getCuriosityImages(
                            token,
                            page,
                            params.loadSize
                        )
                        LoadResult.Page(
                            response.photos,
                            prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                            nextKey = if (response.photos.isEmpty()) 1 else page + 1
                        )
                    }
                }
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photos>): Int? {
        TODO("Not yet implemented")
    }
}