package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// LandingPage landingPage = launchApplication();

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = "submitOrder")
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("vinaykumarvk@gmail.com", "manulife@VK25");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

//		return new Object[][] { { "vinaykumarvk@gmail.com", "manulife@VK25", "ZARA COAT 3" },
//					{ "vinaykumarvk1@gmail.com", "manulife@VK255", "ADIDAS ORIGINAL" } };

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "vinaykumarvk@gmail.com");
//		map.put("password", "manulife@VK25");
//		map.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "vinaykumarvk1@gmail.com");
//		map1.put("password", "manulife@VK255");
//		map1.put("productName", "ADIDAS ORIGINAL");
//
//		return new Object[][] { { map }, { map1 } };
	}

}
