package christmas.domain

enum class Badge(val itemName: String, val standard: Int) {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NONE("없음", 0);

    companion object {
        fun choose(totalBenefit: Int) =
            when (totalBenefit) {
                in STAR.standard until TREE.standard -> STAR
                in TREE.standard until SANTA.standard -> TREE
                in SANTA.standard..Int.MAX_VALUE -> SANTA
                else -> NONE
            }
    }
}
