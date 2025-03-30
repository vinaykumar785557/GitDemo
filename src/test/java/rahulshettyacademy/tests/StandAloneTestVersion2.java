package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StandAloneTestVersion2 {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication("vinaykumarvk@gmail.com", "manulife@VK25");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.quit();
	}

}
