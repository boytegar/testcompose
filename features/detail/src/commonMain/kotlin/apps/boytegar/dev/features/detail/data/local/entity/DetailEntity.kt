package apps.boytegar.dev.features.detail.data.local.entity

data class DetailEntity(
    val id: String,
    val walletId: String,
    val merchantName: String,
    val amount: Double,
    val currency: String,
    val createdAtIso: String,
    val status: String,
)
