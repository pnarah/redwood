package com.cisco.ccbu.automation.scenarios

import com.cisco.ccbu.automation.util.{Environment, Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LogoutUser {

  val uri2 = Environment.btsUrl
  val logoutuserscenario = scenario("LoginUser")
    .exec(http("request_320")
      .get("/vvv/cm/has-monitor-requests?ts=1600068956263")
      .headers(Headers.headers_275)
      .check(status.is(401)))

}