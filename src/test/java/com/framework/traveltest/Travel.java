package com.framework.traveltest;
import org.testng.annotations.Test;
import static com.framewok.utility.Keyword.*;
public class Travel {
	@Test(priority=2)
	public void verifyBrowser() {
		//Keyword.openBrowser("Chrome");
		//Keyword.launchURL("https://www.flipkart.com/");
		openBrowser("Chrome");
		launchURL("https://www.flipkart.com/");
		click("css","button._2KpZ6l._2doB4z");
		enterText("css","input[title^='Search']","Kurtis");
		click("css","button.L0Z3Pu");
	}
	@Test(priority=1)
	public void m1() {
		openBrowser("Chrome");
		launchURL("https://www.flipkart.com/");
		click("css","button._2KpZ6l._2doB4z");
		enterText("css","input[title^='Search']","Kids");
		click("css","button.L0Z3Pu");
		System.out.println("Updated");
	}
}
