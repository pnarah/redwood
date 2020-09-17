package com.cisco.ccbu.automation.scenarios

import com.cisco.ccbu.automation.util.{Environment, Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoginUser {

  val btsUrl = Environment.btsUrl+"/idb"

  val feeder = csv("data.csv").circular


  val loginuserStep = feed(feeder)
    .exec(http("LOGIN_TO_BROWSER")
      .get("/")
      .headers(Headers.headers_0).check(status.in(200, 302, 304))
      .resources(http("OAUTH2_REQUEST")
        .get(btsUrl + "/oauth2/v1/xxxxxxxxx")
        .headers(Headers.headers_0),
      ))
    .pause(20)

    // LOGIN ID OF USER
    .exec(http("VALIDATE_EMAIL_ID")
      .post(btsUrl + "/validateEmail")
      .headers(Headers.headers_14)
      .formParam("user", "${user}")
      .formParam("action", "login")
      .formParam("validate", "true")
      .resources(http("VALIDATE_EMAIL")
        .post(btsUrl + "/validateEmail")
        .headers(Headers.headers_14)
        .formParam("user", "${user}")
        .formParam("action", "login")
        .formParam("validate", "true"),
        http("G_LOGIN")
          .post(btsUrl + "/Login")
          .headers(Headers.headers_16)
          .formParam("email", "${user}")
          .formParam("isCookie", "false")
          .formParam("ForceAuth", "false")
          .formParam("cisService", "common")
          .formParam("gotoUrl", Environment.goto)
          .formParam("encodedParamsString", "FFFFFFCVF=")
      ))
    .pause(26)
    // PASSWORD OF USER
    .exec(http("ENTER_USER_PASSWORD")
      .post(btsUrl + "/Login")
      .headers(Headers.headers_16)
      .formParam("IDToken0", "")
      .formParam("IDToken1", "${user}")
      .formParam("IDToken2", "${password}")
      .formParam("IDButton", "Sign in")
      .formParam("goto", Environment.goto)
      .formParam("SunQueryParamsString", Environment.sunQueryParamsString)
      .formParam("encoded", "true")
      .formParam("loginid", "${user}")
      .formParam("isAudioCaptcha", "false")
      .formParam("gx_charset", "UTF-8")
    )
    .pause(20)


  // scenarios
  val loginuser = scenario("Login User").exec(loginuserStep)

}