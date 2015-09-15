package org.apache.http.scala.client

import org.junit.Test

import Http.defaultClient
import Http.get

@Test
class HttpTest {

  @Test
  def testGet(): Unit = {
    get("http://www.esigate.org").execute(println)
    //Await termination
    Thread.sleep(2000)
  }

  @Test
  def testGetWithHeader(): Unit = {
    get("http://www.esigate.org", List(("Header1", "Value1"))).execute(println)
    //Await termination
    Thread.sleep(2000)
  }
}