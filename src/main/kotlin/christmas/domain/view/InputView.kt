package christmas.domain.view

import camp.nextstep.edu.missionutils.Console.readLine

object InputView {
    fun readVisitDate(): Int {
        try {
            println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
            println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
            return readLine().toInt()
        } catch (_: NumberFormatException) {
            throw IllegalArgumentException("Please enter a valid number.")
        }
    }

    fun readProductsToOrder(): List<CreateOrderDto> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val rawInput = readLine()
        val rawOrders = rawInput.split(",").map { it.trim() }

        try {
            return rawOrders
                .map { it.split("-") }
                .map { CreateOrderDto(it[0].trim(), it[1].trim().toInt()) }
        } catch (_: Exception) {
            throw IllegalArgumentException("Please enter your orders in a valid form. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        }
    }
}
