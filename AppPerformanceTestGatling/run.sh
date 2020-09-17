sbt -DdataCenter=${DATACENTER} -DnoOfSimulatedUsers=${SIMULATEDUSERSCONT} -DmaxResponseTime=${MAXRESPONSETIME} gatling:testOnly com.cisco.ccbu.automation.simulation.${SUITE_TO_SIMULATE}

