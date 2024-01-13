package br.com.alura.alugames.model

data class Game(
    val title: String,val thumb: String
){
    var description: String? = null
    override fun toString(): String {
        return "My br.com.alura.alugames.model.Game:\n" +
                "Title: $title\n" +
                "Thumb: $thumb\n" +
                "Description: $description"
    }


}