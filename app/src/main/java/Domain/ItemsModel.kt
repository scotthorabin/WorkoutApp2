package Domain


// Class to initialise variables ready to implement variables based on database //
data class ItemsModel(
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val instructions: String = "",
    val categoryId: Int = 0
)
