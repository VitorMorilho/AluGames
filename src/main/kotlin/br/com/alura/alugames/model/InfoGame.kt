package br.com.alura.alugames.model

data class InfoGame(
    val info: InfoApiShark
){
    override fun toString(): String {
        return info.toString()
    }
}