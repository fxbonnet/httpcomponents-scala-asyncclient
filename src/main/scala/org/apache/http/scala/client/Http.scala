package org.apache.http.scala.client

import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.concurrent.FutureCallback
import org.apache.http.impl.nio.client.HttpAsyncClients
import org.apache.http.nio.client.HttpAsyncClient

/**
 *
 */
object Http {

  implicit lazy val defaultClient = {
    val result = HttpAsyncClients.createDefault
    result.start
    result
  }

  case class Response(statusCode: Int, statusText: String, headers: List[(String, String)] = List.empty) {
    override def toString() = s"${statusCode} ${statusText}"
  }

  case class Request(method: String, uri: String) {
    def execute(callback: Response => Unit)(implicit client: HttpAsyncClient): Response = {
      val request = method match {
        case "GET" => new HttpGet(uri)
        case _ => new HttpGet(uri)
      }
      client.execute(request, new FutureCallback[HttpResponse]() {
        override def completed(httpResponse: HttpResponse): Unit = {
          val response = new Response(
            httpResponse.getStatusLine.getStatusCode,
            httpResponse.getStatusLine.getReasonPhrase,
            List.empty)
          callback(response)
        }

        override def failed(ex: Exception): Unit = {
          println(s"Request failed: ${ex}")
        }

        override def cancelled(): Unit = {
          println("Request was cancelled")
        }

      })
      new Response(200, "OK")
    }
  }

  def get(uri: String, headers: List[(String, String)] = List.empty): Request = {
    new Request("GET", uri)
  }
}