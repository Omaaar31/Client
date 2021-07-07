package com.example.projetcommun

import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

val MEDIA_TYPE_JSON = "application/json; charset=utf - 8".toMediaType()

class OkHttpUtils {


    @Throws(Exception::class)
    fun sendGetOkHttpRequest(url: String): String {
        Log.w("tag", "url : $url")
        val client = OkHttpClient()
        //Création de la requete
        val request: Request = Request.Builder().url(url).build()
        //Execution de la requête
        val response: Response = client.newCall(request).execute()
        //Analyse du code retour
        return if (response.code < 200 || response.code >= 300) {
            throw Exception("Réponse du serveur incorrect : " + response.code)
        } else {
            //Résultat de la requete.
            //ATTENTION .string() ne peut être appelée qu’une seule fois.
            response.body!!.string()
        }

        fun sendPostOkHttpRequest(url: String, paramJson: String): String {
            println("url : $url")

            //Corps de la requête
            val body = paramJson.toRequestBody(MEDIA_TYPE_JSON)

            //Création de la requete
            val request = Request.Builder().url(url).post(body).build()

            //Execution de la requête
            val response = client.newCall(request).execute()

            //Analyse du code retour
            return if (response.code !in 200..299) {
                throw Exception("Réponse du serveur incorrect : ${response.code}")
            } else {

                //Résultat de la requete.
                //ATTENTION .string ne peut être appelée qu’une seule fois.
                response.body?.string() ?: ""
            }
        }
    }

}