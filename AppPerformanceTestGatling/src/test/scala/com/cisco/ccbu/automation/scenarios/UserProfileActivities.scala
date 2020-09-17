package com.cisco.ccbu.automation.scenarios


import com.cisco.ccbu.automation.util.{Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object UserProfileActivities {

  val listUserProfile = exec(http("LIST_USER_PROFILE")
      .get("/portal/user-profile/list.html?__ts=1600149102264")
      .headers(Headers.upheaders_2).check(status.in(200, 302, 304))
  )
    .pause(33)
  // OPEN A USER PROFILE

   val openUserProfile = exec(http("OPEN_USER_PROFILE")
      .get("/portal/user-profile/find/AXKEWlMtTEFopobJLn-T/READ?")
      .headers(Headers.upheaders_11).check(status.in(200, 302, 304))
    )
    .pause(13)
    .exec(http("request_31")
      .get("/portal/resources/lib/icheck/skins/flat/blue@2x.png")
      .headers(Headers.upheaders_31))
    .pause(5)
    .exec(http("request_32")
      .get("/portal/resources/lib/jstree/css/32px.png")
      .headers(Headers.upheaders_31))
    .pause(23)



  // USER PROFILE BACK TO LIST
    val closeUserProfile = exec(http("CLOSE_USER_PROFILE")
      .get("/portal/user-profile/cancel?idcccccccc")
      .headers(Headers.upheaders_11).check(status.in(200, 302, 304))
    )
    .pause(26)

      //Scenario
      val userProfileOpenAndClose = scenario("OPEN USER PROFILE AND CLOSE").repeat(3){exec(listUserProfile, openUserProfile, closeUserProfile)}
}
