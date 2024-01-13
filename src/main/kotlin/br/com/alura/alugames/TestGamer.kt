import br.com.alura.alugames.model.Gamer

fun main(){
    val gamer1 = Gamer("Vitor", "vitor@email.com")
    println(gamer1)

    val gamer2 = Gamer("Ana", "ana@email.com", "13/07/2006", "anaC")


    gamer1.let {
        it.birthDate = "04/05/2000"
        it.user = "VitorH"
    }.also {
        println(gamer1.id)
    }
}