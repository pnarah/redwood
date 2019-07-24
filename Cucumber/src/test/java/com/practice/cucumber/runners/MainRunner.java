package com.practice.cucumber.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="features",
				glue= {"com.practice.cucumber.stepdefinations"},
				plugin= {"html:target/cucumber-html-reports",
						"json:target/cucumber-reports.json"})
public class MainRunner {

}
