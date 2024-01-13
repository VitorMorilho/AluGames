package br.com.alura.alugames.model

import java.util.*
import kotlin.random.Random

data class Gamer(
    var name: String,
    var email: String,
){
    var birthDate: String? = null
    var user: String? = null
        set(value) {
            field = value
            if (id.isNullOrBlank()){
                createId()
            }
        }
    var id: String? = null
        private set
    val gamesSearched = mutableListOf<Game?>()
    constructor(
        name: String,
        email: String,
        birthDate: String,
        user: String
    ): this(name, email){
        this.birthDate = birthDate
        this.user = user
        createId()
    }

    private fun createId(){
        val number = Random.nextInt(10000)
        val tag = String.format("%04d", number)

        id = "$user#$tag"
    }

    private fun validateEmail(): String{
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)){
            return email
        }
        throw IllegalArgumentException("Email inválido")
    }

    init {
        if (name.isNullOrBlank()){
            throw IllegalArgumentException("Nome está em branco")
        }
        this.email = validateEmail()
    }
    override fun toString(): String {
        return "Gamer(name='$name', email='$email', birthDate=$birthDate, user=$user, id=$id)"
    }

    companion object{
        fun createGamer(reader: Scanner): Gamer{
            println("Boas vindas ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val name = reader.nextLine()
            println("Digite seu e-mail:")
            val email = reader.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val option = reader.nextLine()

            if (option.equals("y", true)){
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val birthDate = reader.nextLine()
                println("Digite seu nome de usuário:")
                val user = reader.nextLine()
                return Gamer(name, email, birthDate, user)
            }
            return Gamer(name, email)
        }
    }
}
