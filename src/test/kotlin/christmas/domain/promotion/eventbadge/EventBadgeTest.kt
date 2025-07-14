package christmas.domain.promotion.eventbadge

import christmas.domain.money.Currency
import christmas.domain.money.Money
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EventBadgeTest {
    @Test
    fun `santa when benefit price == 20000`() {
        val badge = EventBadge.of(Money.longValueOf(20000, Currency.KRW))
        assert(badge == EventBadge.SANTA)
    }

    @ParameterizedTest
    @ValueSource(longs = [10000, 19999])
    fun `tree when benefit price == `(price: Long) {
        val badge = EventBadge.of(Money.longValueOf(price, Currency.KRW))
        assert(badge == EventBadge.TREE)
    }

    @ParameterizedTest
    @ValueSource(longs = [5000, 9999])
    fun `star when benefit price == `(price: Long) {
        val badge = EventBadge.of(Money.longValueOf(price, Currency.KRW))
        assert(badge == EventBadge.STAR)
    }

    @ParameterizedTest
    @ValueSource(longs = [0, 4999])
    fun `null when benefit price == `(price: Long) {
        val badge = EventBadge.of(Money.longValueOf(price, Currency.KRW))
        assert(badge == null)
    }
}
