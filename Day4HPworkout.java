package hp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;

public class Day4HPworkout {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		ChromeOptions options =new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		WebDriverWait  wait = new WebDriverWait(driver,30);
		
		// Mouse over on the laptops
		WebElement laptop = driver.findElementByLinkText("Laptops");
		Actions builder = new Actions(driver);
		builder.moveToElement(laptop).perform();
		//click on the Pavilion item
		driver.findElementByXPath("(//span[text()='Pavilion'])[1]").click();
		Thread.sleep(2000);
		
		
//		Alert disalert = driver.switchTo().alert();
//		disalert.dismiss();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='optanon-alert-box-close banner-close-button']")));
		driver.findElementByXPath("//button[@title='Close']").click();
		
		
		JavascriptExecutor jsexe = (JavascriptExecutor) driver;
		jsexe.executeScript("javascript:scrollBy(0,300)");
		
		//click on the processor
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		
		System.out.println("Processor is selected");
		
		// click on the i7 processor
		driver.findElementByXPath("//span[text()='Intel Core i7']").click();
		Thread.sleep(2000);
		//select HardDrive capacity more than 1 TB
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("javascript:scrollBy(0,500)");
	    
	    
		driver.findElementByXPath("//span[text()='More than 1 TB']").click();
		System.out.println("HardDrive specification: More than 1TB is selected");
		//driver.findElementByXPath("//button[@class='optanon-alert-box-close banner-close-button']").click();
		Thread.sleep(2000);
		
		// select sort by low to high
		WebElement sortby = driver.findElementById("sorter");
		Select sortoptions = new Select(sortby);
		sortoptions.selectByValue("price_asc");
		Thread.sleep(2000);
		
		//Print the First resulting Product Name and Price 
//		JavascriptExecutor getFR = (JavascriptExecutor)driver;
//		getFR.executeScript("javascript.scrollBy(0,500)");
		
		WebElement FRName= driver.findElementByXPath("(//a[@class='product-item-link'])[1]");
		String getFRName = FRName.getText();
		System.out.println("Name of the first resulting product: " + getFRName);
		
		WebElement FRPrice= driver.findElementByXPath("//span[@id='product-price-9580']");
		String GetFRPrice = FRPrice.getText().replaceAll("\\D", "");
		System.out.println("Price of the first resulting product: "+GetFRPrice);
		System.out.println(GetFRPrice);
		
		// Click on "Add to cart"
		
		driver.findElementByXPath("(//span[text()='Add To Cart'])[1]").click();
		System.out.println("Added to carts");
	
		driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
		// Click on the Shopping cart icon --> Click on View and Edit Cart 
		Thread.sleep(2000);
		
		WebElement opencarticon = driver.findElementByXPath("//a[@class='action showcart']");
		opencarticon.click();
		
		
		//Actions builderopencarticon = new Actions(driver);	
		//builderopencarticon.moveToElement(opencarticon)
		System.out.println("shopping icon clicked");
		
				
				//"//a[@class='action showcart']/parent::div"
		//(//span[text()='My Cart'])[1]
		////a[@class='action showcart active']/parent::div	
		
//		
//		WebElement editcart =driver.findElementByXPath("//div[@id='minicart-content-wrapper']");
//		Actions buildereditcart = new Actions(driver);
//		buildereditcart.moveToElement(editcart).perform();
	
	driver.findElementByXPath("//span[text()='View and edit cart']").click();
	
	//*[@id="minicart-content-wrapper"]/div[2]/div[3]/div/a/span/text()
	Thread.sleep(2000);
	System.out.println("Just edit the shooping cart");
	
	// Check the Shipping Option --> Check availability at Pincode 
	
	JavascriptExecutor viewpincode = (JavascriptExecutor) driver;
	viewpincode.executeScript("javascript:scrollBy(0,500)");
	driver.findElementByName("pincode").sendKeys("600115");
	driver.findElementByXPath("//button[text()='check']").click();
	Thread.sleep(2000);
	
	//
	WebElement producttotalprice = driver.findElementByXPath("(//span[@class='price'])[7]");
	////td[@data-th='Order Total']/parent::tr
	
	//(//span[@class='price'])[7]
	String totalprice=producttotalprice.getText().replaceAll("\\D", "");
	System.out.println("Final price of the product at cart:" +totalprice);
	System.out.println(totalprice);
	
	if(GetFRPrice.equals(totalprice))
	{
		System.out.println("Product Price matches with Total Price");
		driver.findElementById("sendIsCAC").click();
	}
	
	else
	{
		System.out.println("Product Price does not matches with Total Price and hence the further checkout is not done ");
		
	}
	
	JavascriptExecutor placeorder = (JavascriptExecutor) driver;
	placeorder.executeScript("Javascript:scrollBy(0,1000)");
	
	driver.findElementByXPath("//span[text()='Place Order']").click();
	Thread.sleep(2000);
	
	//Print the error message
	String Errormsg = driver.findElementByXPath("//div[@id='customer-email-error']").getText();
	System.out.println("Mandatory messages:"+  Errormsg);
	
	//Close Browser
	driver.quit();
	
	}

}
