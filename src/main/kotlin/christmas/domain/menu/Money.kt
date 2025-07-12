package christmas.domain.menu

data class Money(val money: Long) : Comparable<Money> {

    init {
        require(money >= 0L) {
            "Amount must be positive"
        }
    }

    operator fun plus(money: Money): Money {
        return Money(this.money + money.money)
    }

    operator fun minus(money: Money): Money {
        return Money(this.money - money.money)
    }

    operator fun times(money: Money): Money {
        return Money(this.money * money.money)
    }

    override fun compareTo(other: Money): Int {
        return this.money.compareTo(other.money)
    }
}

fun Iterable<Money>.sum(): Money {
    return this.fold(Money(0L)) { sum, money -> sum + money }
}
