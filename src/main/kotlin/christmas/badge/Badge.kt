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
        fun from(totalBenefitAmount: Int): Badge {
            require(totalBenefitAmount >= NONE.totalDiscountAmountThreshold) {
                "Total Benefit amount must be greater than or equal to ${NONE.totalDiscountAmountThreshold}."
            }
            return entries.findLast { totalBenefitAmount >= it.totalDiscountAmountThreshold }
                ?: NONE
        }
    }
}
