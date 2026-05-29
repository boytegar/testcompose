package apps.boytegar.dev.features.auth.di

import apps.boytegar.dev.features.auth.presentation.AuthViewModel
import dev.zacsweers.metro.BindingContainer
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraph

internal data class AuthConfig(
    val splashDurationMillis: Long = 900,
    val loginDelayMillis: Long = 250,
)

@BindingContainer
internal object AuthBindings {
    @Provides
    fun provideAuthConfig(): AuthConfig = AuthConfig()

    @Provides
    fun provideAuthViewModel(config: AuthConfig): AuthViewModel =
        AuthViewModel(
            splashDurationMillis = config.splashDurationMillis,
            loginDelayMillis = config.loginDelayMillis,
        )
}

@DependencyGraph(bindingContainers = [AuthBindings::class])
internal interface AuthGraph {
    val authViewModel: AuthViewModel
}

object AuthDi {
    fun authViewModel(): AuthViewModel = createGraph<AuthGraph>().authViewModel
}
