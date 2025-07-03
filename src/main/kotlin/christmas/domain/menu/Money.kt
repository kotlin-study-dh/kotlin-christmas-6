package christmas.domain.menu

data class Money(val money: Long) {

    init {
        require(money > 0L) {
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
}