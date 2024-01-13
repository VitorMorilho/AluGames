package br.com.alura.alugames.service

import br.com.alura.alugames.model.InfoGame
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ApiConsumer {
    fun findGame(id: String): InfoGame{
        val host = "https://www.cheapshark.com/api/1.0/games?id=${id}"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(host))
            .build()

        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()
        val gson = Gson()

        val myInfoGameGame = gson.fromJson(json, InfoGame::class.java)

        return myInfoGameGame
    }

}