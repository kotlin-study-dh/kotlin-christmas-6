package christmas.event

import christmas.menu.Menu
import christmas.menu.Order

object GiveawayEvent : AbstractChristmasEvent() {
    private const val MIN_ORDER_AMOUNT = 120_000
    private val giveawayMenus = mapOf(
        Menu.CHAMPAGNE to 1,
    )

    override fun isApplicable(order: Order) = order.totalOrderAmount >= MIN_ORDER_AMOUNT

    override fun calculateInternal(order: Order) =
        giveawayMenus.map { (menu, count) ->
            menu.price * count
        }.sum()

    override fun signature() = EventSignature.GIVEAWAY
}
