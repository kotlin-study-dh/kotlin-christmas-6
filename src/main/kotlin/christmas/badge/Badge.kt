package christmas.badge

enum class Badge(
    val korName: String,
    val totalDiscountAmountThreshold: Int,
) {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
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
