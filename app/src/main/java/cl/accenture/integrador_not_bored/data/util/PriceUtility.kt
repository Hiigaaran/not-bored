package cl.accenture.integrador_not_bored.data.util

class PriceUtility {
    fun priceEvaluation(price: Double): String {
        return when {
            price == .0 -> "Free"
            price <= 0.3 -> "Low"
            price <= 0.6 -> "Medium"
            price > 0.6 -> "High"
            else -> "NaN"
        }
    }
}