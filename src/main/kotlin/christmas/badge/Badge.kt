package christmas.badge

enum class Badge(
    val totalBenefitAmountThreshold: Int,
) {
    NONE(0),
    STAR(5_000),
    TREE(10_000),
    SANTA(20_000),
    ;

    companion object {
        fun from(totalBenefitAmount: Int): Badge {
            require(totalBenefitAmount >= NONE.totalBenefitAmountThreshold) {
                "Total Benefit amount must be greater than or equal to ${NONE.totalBenefitAmountThreshold}."
            }
            return entries.sortedBy { it.totalBenefitAmountThreshold }
                .findLast { totalBenefitAmount >= it.totalBenefitAmountThreshold } ?: NONE
        }
    }
}
