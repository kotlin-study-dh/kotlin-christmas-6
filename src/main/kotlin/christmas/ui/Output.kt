package christmas.ui

object Output {

    fun printMenuSummary(menus: List<String>) {
        val grouped = menus.groupingBy { it }.eachCount()

        println("<주문 메뉴>")
        grouped.forEach { (menu, count) ->
            println("$menu ${count}개")
        }
        println()
    }

    fun printPreDiscountAmount(amount: Long) {
        println("<할인 전 총주문 금액>")
        println("${amount}원")
        println()
    }

    fun printPresent(exist: Boolean) {
        println("<증정 메뉴>")
        if (exist) {
            println("샴페인 1개")
        } else {
            println("없음")
        }
        println()
    }

    fun printDiscountDetails(details: List<Pair<String, Long>>) {
        println("<혜택 내역>")
        if (details.isEmpty()) {
            println("없음")
            println()
            return
        }
        details
            .filter { it.second != 0L }
            .forEach { (key, value) -> println("$key: ${-1 * value}원") }
        println()
    }

    fun printTotalDiscount(amount: Long) {
        println("<총혜택 금액>")
        if (amount == 0L) {
            println("없음")
            println()
            return
        }
        println("${-1 * amount}원")
        println()
    }

    fun printNetPrice(amount: Long) {
        println("<할인 후 예상 결제 금액>")
        println("${amount}원")
        println()
    }

    fun printBadge(badge: String) {
        println("<12월 이벤트 배지>")
        println(badge)
    }
}