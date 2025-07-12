package christmas.controller

import christmas.domain.Reception

data class ReceptionResult(
    val menus: List<String>,
    val reception: Reception,
    val purchaseAmount: Long,
    val discountAmount: Long,
    val reservationDate: Int
)
