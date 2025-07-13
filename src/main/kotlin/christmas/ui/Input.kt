package christmas.ui

import camp.nextstep.edu.missionutils.Console

object Input {

    fun enterReservationDate(): Int {
        println(
            """
            안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
            12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
        """.trimIndent()
        )
        val day = Console.readLine().toIntOrNull() ?: throw IllegalArgumentException(" 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        if (day < 0 || day > 31) {
            throw IllegalArgumentException("존재하지 않는 날짜입니다.")
        }
        return day
    }

    fun enterMenus(): List<String> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        val input = Console.readLine().orEmpty()
        return parseMenuInput(input)
    }

    private fun parseMenuInput(input: String): List<String> {
        if (input.isBlank()) return emptyList()
        val menuTokens = input.split(",")
        return menuTokens.flatMap(::parseMenuToken)
    }

    private fun parseMenuToken(token: String): List<String> {
        val parts = token.split("-").map { it.trim() }
        if (parts.size != 2) return emptyList()

        val name = parts[0]
        val count = parts[1].toIntOrNull() ?: return emptyList()
        if (count <= 0) throw IllegalArgumentException("메뉴는 1개 이상이어야 합니다.")

        return List(count) { name }
    }
}