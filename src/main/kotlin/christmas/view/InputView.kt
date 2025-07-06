package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.menu.Menu

object InputView {
    private const val MENU_NAME_AND_COUNTS_DELIMITER = ","
    private const val MENU_NAME_AND_COUNT_DELIMITER = "-"
    private const val MENU_NAME_AND_COUNT_SIZE = 2
    private const val MENU_NAME_INDEX = 0
    private const val MENU_COUNT_INDEX = 1

    fun readVisitDay(): Int {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        val visitDate = Console.readLine() ?: throw IllegalArgumentException("Visit date is required.")
        return visitDate.toIntOrNull()
            ?: throw IllegalArgumentException("Visit date must be a number.")
    }

    fun readMenuNameAndCounts(): List<Pair<String, Int>> {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요.(e.g. ${Menu.SEAFOOD_PASTA.korName}-2,${Menu.RED_WINE.korName}-1,${Menu.CHOCOLATE_CAKE.korName}-1)")
        val menuNameAndCounts = Console.readLine() ?: throw IllegalArgumentException("Menu and count are required.")
        return menuNameAndCounts.split(MENU_NAME_AND_COUNTS_DELIMITER).asSequence()
            .map { it.trim() }
            .map { menuNameAndCount ->
                menuNameAndCount.split(MENU_NAME_AND_COUNT_DELIMITER).also {
                    require(it.size == MENU_NAME_AND_COUNT_SIZE) {
                        "Each menu and count must be in the format 'menu${MENU_NAME_AND_COUNT_DELIMITER}count'."
                    }
                }
            }
            .map { parts ->
                val menuName = parts[MENU_NAME_INDEX].trim()
                val count = parts[MENU_COUNT_INDEX].toIntOrNull()
                    ?: throw IllegalArgumentException("Count must be a number.")
                menuName to count
            }.toList()
    }
}
