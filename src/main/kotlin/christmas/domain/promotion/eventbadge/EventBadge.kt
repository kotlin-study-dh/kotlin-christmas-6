package christmas.domain.promotion.eventbadge

import christmas.domain.money.Currency
import christmas.domain.money.Money

enum class EventBadge(private val minimumBenefitPrice: Money) {
    SANTA(Money.longValueOf(20000, Currency.KRW)),
    TREE(Money.longValueOf(10000, Currency.KRW)),
    STAR(Money.longValueOf(5000, Currency.KRW)),
    ;

    companion object {
        fun of(benefitPrice: Money): EventBadge? {
            return entries.find { benefitPrice >= it.minimumBenefitPrice }
        }
    }
}
