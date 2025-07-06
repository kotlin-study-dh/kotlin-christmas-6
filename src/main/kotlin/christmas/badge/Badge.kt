package christmas.badge

enum class Badge(
    val totalDiscountAmountThreshold: Int,
) {
    NONE(0),
    STAR(5_000),
    TREE(10_000),
    SANTA(20_000),
    ;

    companion object {
        fun from(totalDiscountAmount: Int): Badge {
            require(totalDiscountAmount >= NONE.totalDiscountAmountThreshold) {
                "Total discount amount must be greater than or equal to ${NONE.totalDiscountAmountThreshold}."
            }
            return entries.findLast { totalDiscountAmount >= it.totalDiscountAmountThreshold }
                ?: NONE
        }
    }
}
