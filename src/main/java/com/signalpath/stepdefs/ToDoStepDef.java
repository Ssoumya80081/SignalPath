package com.signalpath.stepdefs;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.signalpath.base.framework.LocatorTypes;
import com.signalpath.base.framework.SeleniumUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ToDoStepDef extends SeleniumUtils{

	String toDoName = "ToDo" + Double.toString(Math.random());

	/*
	 * @Before public void beforeScenario() { launchBrowser(); }
	 */
	
	@Given("I have already on the ToDo Page")
	public void i_have_already_on_the_todo_page()
	{
		launchBrowser();
		Assert.assertTrue(verifyPageTitle("Angular2"), "Expected page tile not found");
	}
	
	
	@When("I enter a ToDo name in search box and press enter")
	public void i_enter_a_to_do_name_in_search_box_press_enter() {
		WebElement txtBox = getElement(LocatorTypes.XPATH, "//input[contains(@placeholder,'What needs to be done?')]");
		enterText(LocatorTypes.XPATH, "//input[contains(@placeholder,'What needs to be done?')]", toDoName);
		pressEnter(txtBox);
	}

	@Then("I validate the ToDo created in the list")
	public void i_validate_the_to_do_created_in_the_list() {
		boolean flag = isElementDisplayed(LocatorTypes.XPATH, "//label[text()='" + toDoName + "']");
		Assert.assertTrue(flag, "Failed to see the added ToDo in the list");
	}

	@When("I click on the ToDo checkbox to complete it")
	public void i_click_on_the_to_do_checkbox_to_complete_it() {
		clickOnElement(LocatorTypes.XPATH, "//label[text()='" + toDoName + "']/preceding-sibling::input");
	}

	@Then("I validate the ToDo is completed")
	public void i_validate_the_to_do_is_completed() {
		String actualResult = getAttributeValue(LocatorTypes.XPATH, "//label[text()='" + toDoName + "']/ancestor::li", "class");
		String expectedResult = "completed";
		Assert.assertEquals(actualResult, expectedResult, "Actual Result and Expected result doesn't match");
	}

	@When("I mouse over on the ToDo")
	public void i_mouse_over_on_the_to_do() {
		mouseOverElement(LocatorTypes.XPATH, "//label[text()='" + toDoName + "']");
	}

	@When("I click on the Cancel button")
	public void i_click_on_the_cancel_button() {
		clickOnElement(LocatorTypes.XPATH, "//label[text()='" + toDoName + "']/following-sibling::button");
	}

	@Then("I validate the ToDo is deleted from the list")
	public void i_validate_the_to_do_is_deleted_from_the_list() {
		List<WebElement> toDoList =getElements(LocatorTypes.XPATH, "//ul[@class='todo-list']/li");
		Assert.assertEquals(toDoList.size(), 0, "ToDo list is not empty, created ToDo is not deleted");

	}
	
	/*
	 * @After public void afterScenario() { quitBrowser(); }
	 */

}
