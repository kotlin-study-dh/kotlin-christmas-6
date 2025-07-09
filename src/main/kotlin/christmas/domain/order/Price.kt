package christmas.domain.order

data class Price(val value: Double): Comparable<Price> {
    init {
        require(value >= 0) { "Value  must be non-negative: $value" }
    }

    infix fun times(operand: Int) = Price(value * operand)
    infix fun plus(other: Price) = Price(value + other.value)
    infix fun minus(other: Price) = Price(value - other.value)

    override fun toString(): String = String.format("%,d", value.toInt())

    override fun compareTo(other: Price): Int = value.compareTo(other.value)

    companion object {
        fun from(value: Int) = Price(value.toDouble())
    }
}

fun Iterable<Price>.sum(): Price = Price(sumOf { it.value })