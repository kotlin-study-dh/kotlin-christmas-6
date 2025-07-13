package christmas.domain

import christmas.domain.menu.Money

enum class Badge(val minimum: Money, val title: String) {

    SANTA_BADGE(Money(20_000), "산타"),
    TREE_BADGE(Money(10_000), "트리"),
    STAR_BADGE(Money(5_000), "스타"),
    NONE(Money(0), "없음");

    companion object {

        fun from(money: Money): Badge {
            return when {
                money > SANTA_BADGE.minimum -> SANTA_BADGE
                money > TREE_BADGE.minimum -> TREE_BADGE
                money > STAR_BADGE.minimum -> STAR_BADGE
                else -> NONE
            }
        }
    }
}