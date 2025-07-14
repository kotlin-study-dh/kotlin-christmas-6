package christmas

import christmas.service.OrderService
import christmas.service.PromotionService
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val visitDate = InputView.readVisitDate()
    val ordersDto = InputView.readProductsToOrder()
    val orders = OrderService.order(visitDate, ordersDto)

    OutputView.printEventDescription(12, visitDate)
    OutputView.printOrderDetails(orders)
    OutputView.printTotalPriceBeforeDiscount(orders.totalPrice())

    val promotionBenefits = PromotionService.findPromotionBenefits(orders)
    val giveawayProduct = PromotionService.findGiveawayProduct(promotionBenefits)
    val benefitPrice = PromotionService.findBenefitPrice(promotionBenefits)
    val discountPrice = PromotionService.findDiscountPrice(promotionBenefits)

    OutputView.printGiveawayProduct(giveawayProduct)
    OutputView.printPromotionBenefits(promotionBenefits)
    OutputView.printBenefitPrice(benefitPrice)
    OutputView.printPaymentPrice(orders.totalPrice().subtract(discountPrice))
}
