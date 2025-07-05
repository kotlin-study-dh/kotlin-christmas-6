package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.domain.Menu
import christmas.domain.order.Order
import java.time.LocalDate

object InputView {
    fun readOrder(): Order {
        println("Hello! This is the December Event Planner for the Woowa Restaurant.")
        return Order(readVisitDate(), readMenu())
    }

    private fun readVisitDate(): LocalDate {
        println("When do you plan to visit the restaurant in December? (Please enter numbers only!)")
        retry {
            val day = Console.readLine().toInt()
            require(day in 1..31) { "Invalid day. Please enter the day again." }
            return LocalDate.of(2023, 12, day)
        }
    }

    private fun readMenu(): Map<Menu, Int> {
        println(
            "Please tell us the menu items you’d like to order and their quantities.\n" +
                    "(e.g. Seafood Pasta-2,Red Wine-1,Chocolate Cake-1)"
        )
        retry {
            return Console.readLine().split(",")
                .associate { it ->
                    val split = it.split("-")
                    val menu = Menu.from(split[0])
                        ?: throw IllegalArgumentException("Invalid menu name. Please enter the displayed name exactly.")
                    val quantity = split[1].toInt()
                    menu to quantity
                }
        }
    }

    inline fun <T> retry(
        block: () -> T
    ): T {
        while (true) {
            try {
                return block()
            } catch (exception: Exception) {
                println("[ERROR] ${exception.message}")
            }
        }
    }
}