package apps.boytegar.dev.features.auth.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import apps.boytegar.dev.core.ui.primitives.CoreCard
import apps.boytegar.dev.core.ui.theme.CoreSpacingTokens

@Composable
fun AuthSplashLayout() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CoreCard(
            modifier = Modifier.fillMaxWidth().padding(CoreSpacingTokens.Lg),
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm),
            ) {
                CircularProgressIndicator()
                Text(
                    text = "Menyiapkan ruang kerja",
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "Membuka pengalaman login yang lebih tenang dan rapi.",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
fun AuthLoginLayout(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(CoreSpacingTokens.Lg),
    ) {
        val isWideLayout = maxWidth >= 720.dp

        if (isWideLayout) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Lg),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IntroPanel(
                    modifier = Modifier.weight(0.95f),
                )
                LoginFormCard(
                    modifier = Modifier.weight(1.05f),
                    username = username,
                    password = password,
                    onUsernameChange = onUsernameChange,
                    onPasswordChange = onPasswordChange,
                    onLoginClick = onLoginClick,
                )
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                LoginFormCard(
                    modifier = Modifier.fillMaxWidth(),
                    username = username,
                    password = password,
                    onUsernameChange = onUsernameChange,
                    onPasswordChange = onPasswordChange,
                    onLoginClick = onLoginClick,
                )
            }
        }
    }
}

@Composable
private fun IntroPanel(
    modifier: Modifier = Modifier,
) {
    CoreCard(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md)) {
            Text(
                text = "Private gallery",
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = "Login dulu, lalu lanjut ke pengalaman yang lebih rapi.",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Akses Home, Favorites, dan detail foto dalam satu alur yang tenang dan tidak terasa generik.",
                style = MaterialTheme.typography.bodyMedium,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm)) {
                FeatureChip(text = "Home")
                FeatureChip(text = "Favorites")
                FeatureChip(text = "Detail")
            }
        }
    }
}

@Composable
private fun LoginFormCard(
    modifier: Modifier = Modifier,
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    CoreCard(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Md),
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Username dan password hanya pintu masuk awal untuk demo aplikasi.",
                style = MaterialTheme.typography.bodyMedium,
            )
            OutlinedTextField(
                value = username,
                onValueChange = onUsernameChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Username") },
                singleLine = true,
            )
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
            )
            Button(
                onClick = onLoginClick,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Masuk")
            }
            Text(
                text = "Setelah masuk, alur berpindah otomatis ke Home.",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
private fun FeatureChip(text: String) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.72f),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        )
    }
}
