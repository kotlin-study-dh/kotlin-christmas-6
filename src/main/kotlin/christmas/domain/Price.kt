package christmas.domain

data class Price(val value: Double) {
    init {
        require(value >= 0) { "Value  must be non-negative: $value" }
    }

    companion object {
        fun from(value: Int) =  Price(value.toDouble())
    }
}
