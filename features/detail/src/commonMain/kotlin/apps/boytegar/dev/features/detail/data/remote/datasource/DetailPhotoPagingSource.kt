package apps.boytegar.dev.features.detail.data.remote.datasource

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingSource
import androidx.paging.PagingState
import apps.boytegar.dev.features.detail.data.mapper.toDomain
import apps.boytegar.dev.features.detail.domain.model.DetailPhoto

internal class DetailPhotoPagingSource(
    private val remoteDataSource: DetailRemoteDataSource,
) : PagingSource<Int, DetailPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DetailPhoto> {
        val page = params.key ?: 1
        return try {
            val limit = params.loadSize.coerceAtLeast(1)
            val photos = remoteDataSource.getPhotos(page = page, limit = limit)
                .map { it.toDomain() }

            LoadResult.Page(
                data = photos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (photos.size < limit) null else page + 1,
            )
        } catch (throwable: Throwable) {
            LoadResult.Error(throwable)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DetailPhoto>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition) ?: return null
        return closestPage.prevKey?.plus(1) ?: closestPage.nextKey?.minus(1)
    }
}
