package christmas.domain.order

import christmas.domain.money.Money
import christmas.domain.product.ProductType
import java.time.LocalDate

data class Orders(
    val date: LocalDate,
    val orders: List<Order>,
) {
    companion object {
        private const val MIN_ORDER_AMOUNT = 1
        private const val MAX_ORDER_AMOUNT = 20
    }

    init {
        validateOrderNotEmpty()
        validateProductTypes()
        validateOrderAmount()
    }

    fun totalPrice(): Money {
        val totalAmount = orders.sumOf { it.orderPrice().amount }
        val currency = orders.first().currency()
        return Money(totalAmount, currency)
    }

    private fun validateOrderNotEmpty() {
        require(orders.isNotEmpty()) { "Please order at least one product." }
    }

    private fun validateProductTypes() {
        if (orders.all { it.product.productType == ProductType.DRINK }) {
            throw IllegalArgumentException("It is not allowed to order only drinks.")
        }
    }

    private fun validateOrderAmount() {
        val orderAmount = orders.sumOf { it.amount }
        require(orderAmount in MIN_ORDER_AMOUNT..MAX_ORDER_AMOUNT) {
            "Order amount should be between $MIN_ORDER_AMOUNT and $MAX_ORDER_AMOUNT."
        }
    }
}
