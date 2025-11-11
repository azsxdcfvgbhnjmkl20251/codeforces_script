package com.example;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.datatable.DataTable;

import java.util.List;
import static org.junit.Assert.*;

public class StepDefinitions {
    private int result;
    private Calculator calculator;

    @Before
    public void setup() {
        System.out.println("Starting scenario...");
    }

    @After
    public void teardown() {
        System.out.println("Scenario finished.");
    }

    @Given("I have a calculator")
    public void i_have_a_calculator() {
        calculator = new Calculator();
    }

    @When("I add {int} and {int}")
    public void i_add_and(int num1, int num2) {
        result = calculator.add(num1, num2);
    }

    @Then("the result should be {int}")
    public void the_result_should_be(int expectedResult) {
        assertEquals(expectedResult, result);
    }

    @When("I add the following numbers:")
    public void i_add_the_following_numbers(DataTable dataTable) {
        List<Integer> numbers = dataTable.asList(Integer.class);
        result = numbers.stream().mapToInt(Integer::intValue).sum();
    }

    @Then("the result should equal {int}")
    public void the_result_should_equal(int expected) {
        assertEquals(expected, result);
    }
}
