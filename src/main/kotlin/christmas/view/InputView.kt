package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.domain.Menu

object InputView {

    fun readDate(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        return Console.readLine().toIntOrNull()
            ?: throw IllegalArgumentException("invalid date")
    }

    fun readMenu(): Map<Menu, Int> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        return Console.readLine().replace(" ", "").split(",")
            .associate { item ->
                val parts = item.split("-")
                require(parts.size == 2) { "invalid order format: $item" }
                val menuName = parts[0]
                val quantity = parts[1].toIntOrNull()
                    ?: throw IllegalArgumentException("quantity must be a positive number")
                val menu = Menu.findMenu(menuName)
                menu to quantity
            }
    }
}
