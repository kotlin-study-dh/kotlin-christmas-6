package christmas.domain.discount

import christmas.domain.menu.Money

interface Discounter {

    fun discount(): Money
}