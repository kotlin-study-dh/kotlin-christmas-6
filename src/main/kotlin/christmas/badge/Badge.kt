package christmas.badge

enum class Badge(
    val korName: String,
    val totalDiscountAmountThreshold: Int,
) {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
    ;

    companion object {
        fun from(totalDiscountAmount: Int): Badge {
            return entries.findLast { totalDiscountAmount >= it.totalDiscountAmountThreshold }
                ?: throw IllegalArgumentException("Cannot find badge for total discount amount: $totalDiscountAmount")
        }
    }
}
