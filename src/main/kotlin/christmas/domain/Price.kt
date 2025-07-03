package christmas.domain

data class Price(val value: Double) {
    init {
        require(value >= 0) { "Value  must be non-negative: $value" }
    }

    infix fun times(operand: Int) = Price(value * operand)

    companion object {
        fun from(value: Int) = Price(value.toDouble())
    }
}

fun Iterable<Price>.sum(): Price = Price(sumOf { it.value })