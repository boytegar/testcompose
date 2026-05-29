package apps.boytegar.dev.features.home.di

import apps.boytegar.dev.core.network.config.NetworkConfig
import apps.boytegar.dev.core.network.config.provideHttpClient as coreProvideHttpClient
import apps.boytegar.dev.core.network.config.provideKtorfit as coreProvideKtorfit
import apps.boytegar.dev.core.network.config.provideNetworkConfig as coreProvideNetworkConfig
import apps.boytegar.dev.features.home.data.local.datasource.HomeLocalDataSource
import apps.boytegar.dev.features.home.data.local.datasource.InMemoryHomeLocalDataSource
import apps.boytegar.dev.features.home.data.remote.api.HomeApi
import apps.boytegar.dev.features.home.data.remote.api.createHomeApi
import apps.boytegar.dev.features.home.data.remote.datasource.HomeRemoteDataSource
import apps.boytegar.dev.features.home.data.remote.datasource.HomeRemoteDataSourceImpl
import apps.boytegar.dev.features.home.data.repository.HomeRepositoryImpl
import apps.boytegar.dev.features.home.domain.repository.HomeRepository
import apps.boytegar.dev.features.home.presentation.viewmodel.HomeViewModel
import apps.boytegar.dev.shared.constants.HOME_BASE_URL
import de.jensklingenberg.ktorfit.Ktorfit
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraph
import io.ktor.client.HttpClient

@BindingContainer
internal object HomeBindings {
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
    fun provideHomeApi(ktorfit: Ktorfit): HomeApi = ktorfit.createHomeApi()

    @Provides
    fun provideHomeRemoteDataSource(
        remoteDataSource: HomeRemoteDataSourceImpl,
    ): HomeRemoteDataSource = remoteDataSource

    @Provides
    fun provideHomeLocalDataSource(
        localDataSource: InMemoryHomeLocalDataSource,
    ): HomeLocalDataSource = localDataSource

    @Provides
    fun provideHomeRepository(repository: HomeRepositoryImpl): HomeRepository = repository
}

@DependencyGraph(bindingContainers = [HomeBindings::class])
internal interface HomeGraph {
    val homeViewModel: HomeViewModel
}

internal object HomeDi {
    fun homeViewModel(): HomeViewModel = createGraph<HomeGraph>().homeViewModel
}
