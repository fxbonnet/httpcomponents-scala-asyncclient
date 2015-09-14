package org.apache.http.scala.client

import org.junit.Assert.assertTrue
import org.junit.Test
import Http._

@Test
class HttpTest {
  @Test
  def testGet() = get("http://www.apache.org")

}