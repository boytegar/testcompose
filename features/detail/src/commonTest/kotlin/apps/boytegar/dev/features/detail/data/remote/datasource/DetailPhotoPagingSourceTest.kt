package apps.boytegar.dev.features.detail.data.remote.datasource

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import apps.boytegar.dev.features.detail.data.remote.dto.DetailPhotoResponseDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class DetailPhotoPagingSourceTest {
    private val remoteDataSource = object : DetailRemoteDataSource {
        override suspend fun getPhotos(page: Int, limit: Int): List<DetailPhotoResponseDto> =
            List(limit) { index ->
                val id = ((page - 1) * limit) + index + 1
                DetailPhotoResponseDto(
                    albumId = page,
                    id = id,
                    title = "Photo $id",
                    url = "https://example.com/photos/$id",
                    thumbnailUrl = "https://example.com/photos/thumb/$id",
                )
            }
    }

    @Test
    fun `load first page returns data and next key`() = runTest {
        val source = DetailPhotoPagingSource(remoteDataSource)

        val result = source.load(
            LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        assertTrue(result is LoadResult.Page)
        result as LoadResult.Page
        assertEquals(20, result.data.size)
        assertEquals(null, result.prevKey)
        assertEquals(2, result.nextKey)
        assertEquals(1, result.data.first().albumId)
        assertEquals("Photo 1", result.data.first().title)
    }
}
