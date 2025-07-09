package christmas.domain

class Date(val date: Int) {

    init {
        require(date in 1..31) {
            throw IllegalArgumentException("date must be between 1 and 31")
        }
    }
}
