package com.cisco.ccbu.automation.util

import java.util.Properties
import scala.io.Source

object Environment {
  val dcProperties = new Properties();
  val dataCenter = scala.util.Properties.envOrElse("dataCenter", "qaus1")
  val propertiesFileURL = getClass.getResource("/"+dataCenter +".properties")

  println("=====>>>>>>>>>>>>>>>>>>======dataCenter :"+ dataCenter +" \n =====>>>>>>>>>>>>>>>>>>====== propertiesFileURL : " + propertiesFileURL)
  val sourceProp = Source.fromURL(propertiesFileURL)

  dcProperties.load(sourceProp.bufferedReader())

  val analyzerUrl = dcProperties.getProperty("ANALYZER_BASE_URL", "https://analyzer.com")
  val btsUrl =  dcProperties.getProperty("BTS_URL", "https://idb.com")
  val loginId = dcProperties.getProperty("LOGIN_ID", "aaaa@mail.com")
  val pcode = dcProperties.getProperty("PCODE", "Applw")
  val goto = dcProperties.getProperty("GOTO", "dddddd")
  val sunQueryParamsString = dcProperties.getProperty("SUN_QUERY_PARAM_STRING", "ddd")


  val portalUrl = dcProperties.getProperty("PORTAL_URL", "https://portal.com")
  val portalUserId = dcProperties.getProperty("PORTAL_USER_LOGINID", "aaaaa@mailinator.com")
  val portalUserPawd = dcProperties.getProperty("PORTAL_USER_PWD", "W23")


  val maxUsersSimulation = scala.util.Properties.envOrElse("noOfSimulatedUsers", "100")
  val maxResponseTIme = scala.util.Properties.envOrElse("maxResponseTime","1200") // In milli-seconds

}
