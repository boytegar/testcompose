package apps.boytegar.dev.features.detail.di

import apps.boytegar.dev.core.network.config.NetworkConfig
import apps.boytegar.dev.core.network.config.provideHttpClient as coreProvideHttpClient
import apps.boytegar.dev.core.network.config.provideKtorfit as coreProvideKtorfit
import apps.boytegar.dev.core.network.config.provideNetworkConfig as coreProvideNetworkConfig
import apps.boytegar.dev.features.detail.data.remote.api.DetailApi
import apps.boytegar.dev.features.detail.data.remote.api.createDetailApi
import apps.boytegar.dev.features.detail.data.remote.datasource.DetailRemoteDataSource
import apps.boytegar.dev.features.detail.data.remote.datasource.DetailRemoteDataSourceImpl
import apps.boytegar.dev.features.detail.data.repository.DetailRepositoryImpl
import apps.boytegar.dev.features.detail.domain.repository.DetailRepository
import apps.boytegar.dev.features.detail.presentation.viewmodel.DetailViewModel
import apps.boytegar.dev.shared.constants.HOME_BASE_URL
import de.jensklingenberg.ktorfit.Ktorfit
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraph
import io.ktor.client.HttpClient

@BindingContainer
internal object DetailBindings {
    @Provides
    fun provideNetworkConfig(): NetworkConfig = coreProvideNetworkConfig(HOME_BASE_URL)

    @Provides
    fun provideHttpClient(config: NetworkConfig): HttpClient = coreProvideHttpClient(config)

    @Provides
    fun provideKtorfit(
        config: NetworkConfig,
        httpClient: HttpClient,
    ): Ktorfit = coreProvideKtorfit(config, httpClient)

    @Provides
    fun provideDetailApi(ktorfit: Ktorfit): DetailApi = ktorfit.createDetailApi()

    @Provides
    fun provideDetailRemoteDataSource(
        remoteDataSource: DetailRemoteDataSourceImpl,
    ): DetailRemoteDataSource = remoteDataSource

    @Provides
    fun provideDetailRepository(repository: DetailRepositoryImpl): DetailRepository = repository
}

@DependencyGraph(bindingContainers = [DetailBindings::class])
internal interface DetailGraph {
    val detailViewModel: DetailViewModel
}

internal object DetailDi {
    fun detailViewModel(): DetailViewModel = createGraph<DetailGraph>().detailViewModel
}
