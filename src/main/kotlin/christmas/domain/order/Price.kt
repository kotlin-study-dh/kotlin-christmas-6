package christmas.domain.order

data class Price(val value: Double): Comparable<Price> {
    init {
        require(value >= 0) { "Value  must be non-negative: $value" }
    }

    operator fun plus(other: Price) = Price(value + other.value)

    operator fun minus(other: Price) = Price(value - other.value)

    operator fun times(operand: Int) = Price(value * operand)

    override fun compareTo(other: Price): Int = value.compareTo(other.value)

    override fun toString(): String = String.format("%,d", value.toInt())


    companion object {
        fun from(value: Int) = Price(value.toDouble())
    }
}

fun Iterable<Price>.sum(): Price = Price(sumOf { it.value })