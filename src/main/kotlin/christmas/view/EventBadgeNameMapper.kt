package christmas.view

import christmas.domain.promotion.eventbadge.EventBadge

object EventBadgeNameMapper {
    private val names = mapOf(
        EventBadge.SANTA to "산타",
        EventBadge.STAR to "별",
        EventBadge.TREE to "트리",
    )

    fun map(eventBadge: EventBadge?): String {
        return names[eventBadge] ?: "없음"
    }
}
