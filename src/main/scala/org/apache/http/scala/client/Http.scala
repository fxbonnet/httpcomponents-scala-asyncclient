package org.apache.http.scala.client

import org.apache.http.impl.nio.client.HttpAsyncClients
import org.apache.http.nio.client.HttpAsyncClient

/**
 *
 */
object Http {
  implicit def defaultClient = HttpAsyncClients.createDefault
  case class HttpResponse(statusCode: Int, statusText: String, headers: List[(String, String)])
  case class HttpRequest(method: String, uri: String)
  def get(
    uri: String,
    headers: List[(String, String)] = List.empty)(implicit httpClient: HttpAsyncClient) {

  }
}