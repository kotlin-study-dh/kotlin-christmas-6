package christmas.view

import christmas.configuration.promotion.*

object PromotionNameMapper {
    private val names = mapOf(
        ChampagneGiveaway to "증정 이벤트",
        ChristmasDdayDiscount to "크리스마스 디데이 할인",
        StarDiscount to "특별 할인",
        WeekdayDessertDiscount to "평일 할인",
        WeekendMainDishDiscount to "주말 할인",
    )

    fun map(promotion: Promotion): String? {
        return names[promotion] ?: throw IllegalStateException()
    }
}
