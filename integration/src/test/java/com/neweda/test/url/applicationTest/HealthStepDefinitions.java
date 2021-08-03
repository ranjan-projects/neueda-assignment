package com.neweda.test.url.applicationTest;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HealthStepDefinitions extends CucumberRoot {

    private ResponseEntity<String> response; // output

    @When("^the client calls /health$")
    public void the_client_issues_GET_health() throws Throwable {
        response = template.getForEntity("/health", String.class);
    }

    @Then("^the client receives response status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) {
        HttpStatus currentStatusCode = response.getStatusCode();
        assertThat("status code is incorrect : " + response.getBody(), currentStatusCode.value(), is(statusCode));
    }
}
