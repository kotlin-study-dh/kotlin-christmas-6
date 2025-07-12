package christmas.controller

object ErrorHandler {

    fun <T> handle(callback: () -> T): T {
        try {
            return callback()
        } catch (e: Exception) {
            println("[ERROR]" + e.message)
            println()
            return handle(callback)
        }
    }
}