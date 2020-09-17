package com.cisco.ccbu.automation.simulation

import com.cisco.ccbu.automation.scenarios.{LoginUser, UserProfileActivities}
import com.cisco.ccbu.automation.util.{Environment, Headers}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PortaActivitySimulation extends Simulation{

  val httpConf = http.baseUrl(Environment.portalUrl)
    .headers(Headers.commonHeader)

  val portalScenarios = List(
    LoginUser.loginuser.inject(
                nothingFor(4),
                atOnceUsers(1),
                rampUsersPerSec(1) to 100 during(300 )
              ),
      UserProfileActivities.userProfileOpenAndClose.inject(
                nothingFor(15),
                atOnceUsers(1),
                rampUsersPerSec(1) to 100 during(300 ))
                .throttle(
                        reachRps(100) in (60),
                        holdFor(60 ),
                        jumpToRps(50),
                        holdFor(600)
                )
  )

  setUp(portalScenarios)
    .protocols(httpConf)
   // .maxDuration(300)
    .assertions(
      global.responseTime.max.lt(Environment.maxResponseTIme.toInt)
    )

}

