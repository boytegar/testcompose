package apps.boytegar.dev.shared.utils

import kotlin.math.round

fun format2Decimals(value: Double): String {
    val rounded = round(value * 100.0) / 100.0
    val s = rounded.toString()
    val parts = s.split('.')
    val decimals = if (parts.size > 1) parts[1].padEnd(2, '0').take(2) else "00"
    return parts[0] + "." + decimals
}

 object NumberFormat {
    fun formatCurrency(value: Double): String {
        val formatted = formatNumberWithCommas(value)
        return "\$$formatted"
    }

    fun formatLargeNumber(value: Double): String {
        val (num, suffix) = when {
            value >= 1_000_000_000_000 -> value / 1_000_000_000_000 to "T"
            value >= 1_000_000_000 -> value / 1_000_000_000 to "B"
            value >= 1_000_000 -> value / 1_000_000 to "M"
            value >= 1_000 -> value / 1_000 to "K"
            else -> value to ""
        }
        return "\$${formatNumberWithCommas(num)}$suffix"
    }

    fun formatNumber(value: Double): String {
        val (num, suffix) = when {
            value >= 1_000_000_000_000 -> value / 1_000_000_000_000 to "T"
            value >= 1_000_000_000 -> value / 1_000_000_000 to "B"
            value >= 1_000_000 -> value / 1_000_000 to "M"
            value >= 1_000 -> value / 1_000 to "K"
            else -> value to ""
        }
        return "${formatNumberWithCommas(num)}$suffix"
    }

    private fun formatNumberWithCommas(value: Double): String {
        val rounded = round(value * 100.0) / 100.0
        val parts = rounded.toString().split('.')
        val integerPart = parts[0]
        val decimalPart = if (parts.size > 1) {
            parts[1].take(2).padEnd(2, '0')
        } else {
            "00"
        }
        val withCommas = StringBuilder()
        var count = 0
        for (i in integerPart.length - 1 downTo 0) {
            if (count > 0 && count % 3 == 0) {
                withCommas.insert(0, ',')
            }
            withCommas.insert(0, integerPart[i])
            count++
        }
        return "$withCommas.$decimalPart"
    }
}