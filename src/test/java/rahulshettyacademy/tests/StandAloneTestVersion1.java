package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestVersion1 {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("vinaykumarvk@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("manulife@VK25");

		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));

		Boolean match = cartProducts.stream().anyMatch(cartproduct -> cartproduct.getText().equals(productName));

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions action = new Actions(driver);

		action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build()
				.perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		;

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.quit();
	}

}

//List<WebElement> elements = driver.findElements(By.cssSelector(".mb-3"));
//
//for (WebElement element : elements) {
//
//	String product = element.findElement(By.tagName("b")).getText();
//
//	if (product.equalsIgnoreCase("ZARA COAT 3")) {
//
//		element.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//
//	}
//
//}

//List<String> targetProducts = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO");
//
//
//
//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//
//
//
//// Loop through the target products and add them to the cart
//
//targetProducts.forEach(targetProduct -> {
//
//    WebElement product = products.stream()
//
//            .filter(p -> p.findElement(By.cssSelector("b")).getText().equals(targetProduct))
//
//            .findFirst().orElse(null);
//
//    if (product != null) {
//
//        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//
//
//
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class*='ng-animating']"))));
//
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[class*='ngx-spinner-overlay']"))));
//
//    }
//
//});
//
//driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

//select country from payment page

////enter text 'ind' in text box
//
//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
//
////explicit wait till dropdown appears
//
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//
////move all the country in the dropdown to a list
//
//List<WebElement> countryList = driver.findElements(By.cssSelector("button[class*='list-group-item']"));
//
////convert the list to stream and iterate to check if 'India' is there in the list and then collect 'India' to another list
//
//List<WebElement> countrySelected = countryList.stream().filter(country -> country.getText().equalsIgnoreCase("India")).collect(Collectors.toList());
//
////click on India from the dropdownlist
//
//countrySelected.get(0).click();