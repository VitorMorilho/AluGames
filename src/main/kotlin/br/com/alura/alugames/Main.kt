package br.com.alura.alugames

import br.com.alura.alugames.model.Game
import br.com.alura.alugames.model.Gamer
import br.com.alura.alugames.service.ApiConsumer
import turnIntoAge
import java.util.*


fun main() {
    val reader = Scanner(System.`in`)
    val gamer = Gamer.createGamer(reader)
    println("Cadastro com sucesso:")
    println(gamer)
    println("Idade do gamer: " + gamer.birthDate?.turnIntoAge())
    do {
        println("Digite um código de jogo para buscar:")
        val find = reader.nextLine()
        val findApi = ApiConsumer()
        val infoGame = findApi.findGame(find)

        var myGame: Game? = null
        val result = runCatching {
            myGame = Game(infoGame.info.title, infoGame.info.thumb)
            println(myGame)
        }
        result.onFailure {
            println("br.com.alura.alugames.model.Game inexistente, tente outro id:")
        }

        result.onSuccess {
            println("Quer colocar uma descrição personalizada? Y/N")
            val option = reader.nextLine()
            if (option.equals("y", true)){
                println("Coloque sua descrição")
                val customDescription = reader.nextLine()
                myGame?.description = customDescription
            }else{
                myGame?.description = myGame?.title
                println("Então a descrição será o titulo")
            }

            gamer.gamesSearched.add(myGame)
        }
        println("Deseja buscar um novo jogo? Y/N")
        val response = reader.nextLine()
    }while (response.equals("y", true))
    println("Jogos buscados")
    println(gamer.gamesSearched)

    println("\nJogos ordenados por titulos")
    gamer.gamesSearched.sortBy {
        it?.title
    }
    gamer.gamesSearched.forEach{
        println("Titulo: " + it?.title)
    }
    val filteredGames = gamer.gamesSearched.filter {
        it?.title?.contains("batman", true)?: false
    }
    println("\nJogos filtrados")
    println(filteredGames)
    println("\nBusca realizada com sucesso")

    println("Deseja excluir algum da lista original? Y/N")
    val option = reader.nextLine()
    if (option.equals("y", true)){
        println(gamer.gamesSearched)
        println("\nInforme a posição do jogo que deseja excluir:")
        val position = reader.nextInt()
        gamer.gamesSearched.removeAt(position)
    }
    println("\nLista atualizada")
    println(gamer.gamesSearched)

}