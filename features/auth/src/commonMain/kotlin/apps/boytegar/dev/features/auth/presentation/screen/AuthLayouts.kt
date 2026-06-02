package apps.boytegar.dev.features.auth.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.boytegar.dev.core.ui.primitives.PillButton
import apps.boytegar.dev.core.ui.theme.CoreColorTokens
import apps.boytegar.dev.core.ui.theme.CoreSpacingTokens

@Composable
fun AuthSplashLayout() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = CoreSpacingTokens.Lg),
            shape = RoundedCornerShape(32.dp),
            color = CoreColorTokens.Primary.copy(alpha = 0.08f),
        ) {
            Column(
                modifier = Modifier.padding(CoreSpacingTokens.Lg),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm),
            ) {
                Text(
                    text = "Menyiapkan pengalaman login",
                    style = MaterialTheme.typography.titleMedium,
                    color = CoreColorTokens.Primary,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Menyusun halaman masuk yang rapi dan nyaman untuk mobile.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = CoreColorTokens.OnSurface,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun AuthLoginLayout(
    onPhoneLoginClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onFacebookLoginClick: () -> Unit,
    onXLoginClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onCompanyClick: () -> Unit = {},
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(CoreColorTokens.SurfaceLight),
    ) {
        val contentWidth = if (maxWidth > 480.dp) 420.dp else maxWidth

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = CoreSpacingTokens.Lg, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .width(contentWidth)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Lg, Alignment.CenterVertically),
            ) {
                HealthcareIllustration()

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm),
                ) {
                    Text(
                        text = "Layanan kesehatan",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = CoreColorTokens.Primary,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Dapatkan obat dan rujukan penunjang medis dalam genggaman",
                        style = MaterialTheme.typography.bodyMedium,
                        color = CoreColorTokens.OnSurface,
                        textAlign = TextAlign.Center,
                    )
                    CarouselIndicator(
                        modifier = Modifier.padding(top = CoreSpacingTokens.Xs),
                    )
                }

                PillButton(
                    onClick = onPhoneLoginClick,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = CoreColorTokens.Primary,
                    contentColor = CoreColorTokens.OnPrimary,
                ) {
                    Text(
                        text = "Masuk atau Buat akun dengan no HP",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                    )
                }

                DividerWithLabel(text = "atau dengan")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    SocialLoginButton(
                        onClick = onGoogleLoginClick,
                    ) {
                        Text(
                            text = "G",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4285F4),
                        )
                    }
                    SocialLoginButton(
                        onClick = onFacebookLoginClick,
                    ) {
                        Text(
                            text = "f",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1877F2),
                        )
                    }
                    SocialLoginButton(
                        onClick = onXLoginClick,
                    ) {
                        Text(
                            text = "X",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF111111),
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.width(contentWidth),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(CoreSpacingTokens.Sm),
            ) {
                LegalText(
                    onPrivacyPolicyClick = onPrivacyPolicyClick,
                    onTermsClick = onTermsClick,
                )
                CopyrightText(onCompanyClick = onCompanyClick)
            }
        }
    }
}

@Composable
private fun HealthcareIllustration() {
    Box(
        modifier = Modifier
            .size(160.dp)
            .background(
                color = CoreColorTokens.Primary.copy(alpha = 0.06f),
                shape = RoundedCornerShape(44.dp),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(112.dp)) {
            val crossColor = CoreColorTokens.Primary
            val accentColor = CoreColorTokens.AccentTeal
            val center = Offset(size.width / 2f, size.height / 2f)
            val crossThickness = size.minDimension * 0.14f
            val crossLength = size.minDimension * 0.52f

            drawRoundRect(
                color = crossColor,
                topLeft = Offset(center.x - crossThickness / 2f, center.y - crossLength / 2f),
                size = androidx.compose.ui.geometry.Size(crossThickness, crossLength),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(crossThickness / 2f, crossThickness / 2f),
            )
            drawRoundRect(
                color = crossColor,
                topLeft = Offset(center.x - crossLength / 2f, center.y - crossThickness / 2f),
                size = androidx.compose.ui.geometry.Size(crossLength, crossThickness),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(crossThickness / 2f, crossThickness / 2f),
            )

            val pulsePath = Path().apply {
                val startX = size.width * 0.16f
                val startY = size.height * 0.66f
                moveTo(startX, startY)
                lineTo(size.width * 0.28f, startY)
                lineTo(size.width * 0.36f, size.height * 0.50f)
                lineTo(size.width * 0.43f, size.height * 0.76f)
                lineTo(size.width * 0.52f, size.height * 0.58f)
                lineTo(size.width * 0.60f, startY)
                lineTo(size.width * 0.68f, size.height * 0.48f)
                lineTo(size.width * 0.76f, size.height * 0.76f)
                lineTo(size.width * 0.84f, startY)
            }

            drawPath(
                path = pulsePath,
                color = accentColor,
                style = Stroke(
                    width = size.minDimension * 0.042f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                ),
            )
        }
    }
}

@Composable
private fun CarouselIndicator(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(2) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(CoreColorTokens.AccentTeal.copy(alpha = 0.22f), CircleShape),
            )
        }
        Box(
            modifier = Modifier
                .width(26.dp)
                .height(8.dp)
                .background(CoreColorTokens.AccentTeal, RoundedCornerShape(999.dp)),
        )
    }
}

@Composable
private fun DividerWithLabel(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = CoreColorTokens.OnSurface.copy(alpha = 0.20f),
            thickness = 1.dp,
        )
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = CoreSpacingTokens.Sm),
            style = MaterialTheme.typography.labelMedium,
            color = CoreColorTokens.OnSurface,
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = CoreColorTokens.OnSurface.copy(alpha = 0.20f),
            thickness = 1.dp,
        )
    }
}

@Composable
private fun SocialLoginButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = Modifier
            .size(58.dp)
            .clickable(onClick = onClick),
        shape = CircleShape,
        color = Color.White,
        shadowElevation = 6.dp,
    ) {
        Box(contentAlignment = Alignment.Center) {
            content()
        }
    }
}

@Composable
private fun LegalText(
    onPrivacyPolicyClick: () -> Unit,
    onTermsClick: () -> Unit,
) {
    val annotatedText = buildAnnotatedString {
        append("Dengan menggunakan aplikasi, anda setuju dengan ")
        pushStringAnnotation(tag = "privacy", annotation = "privacy")
        withStyle(SpanStyle(color = CoreColorTokens.Primary, fontWeight = FontWeight.SemiBold)) {
            append("Kebijakan Privasi")
        }
        pop()
        append(" dan ")
        pushStringAnnotation(tag = "terms", annotation = "terms")
        withStyle(SpanStyle(color = CoreColorTokens.Primary, fontWeight = FontWeight.SemiBold)) {
            append("Ketentuan Aplikasi")
        }
        pop()
        append(" kami.")
    }

    LinkParagraph(
        text = annotatedText,
        onLinkClick = { tag ->
            when (tag) {
                "privacy" -> onPrivacyPolicyClick()
                "terms" -> onTermsClick()
            }
        },
    )
}

@Composable
private fun CopyrightText(onCompanyClick: () -> Unit) {
    val annotatedText = buildAnnotatedString {
        append("Hak cipta pembuatan aplikasi ")
        pushStringAnnotation(tag = "company", annotation = "company")
        withStyle(SpanStyle(color = CoreColorTokens.Primary, fontWeight = FontWeight.SemiBold)) {
            append("PT. Biofarma")
        }
        pop()
    }

    LinkParagraph(
        text = annotatedText,
        onLinkClick = { tag ->
            if (tag == "company") {
                onCompanyClick()
            }
        },
    )
}

@Composable
private fun LinkParagraph(
    text: AnnotatedString,
    onLinkClick: (String) -> Unit,
) {
    ClickableText(
        text = text,
        style = MaterialTheme.typography.bodySmall.copy(
            color = CoreColorTokens.OnSurface,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
        ),
        onClick = { offset ->
            text.getStringAnnotations(start = offset, end = offset)
                .firstOrNull()
                ?.let { onLinkClick(it.tag) }
        },
    )
}
