package ajio;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ajioTestCase {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(option);
		System.setProperty("silentoutput","SILENT");

		
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 
		//Enter Bags in the Search field and Select Bags in Women Handbags 
		driver.findElementByXPath("//input[@class='react-autosuggest__input react-autosuggest__input--open']").sendKeys("Bags");
		Thread.sleep(2000);
		
		WebElement Wbags = driver.findElementByXPath("(//span[text()='Bags in '])[1]");
		Actions builderWbags = new Actions(driver);
		
		builderWbags.moveToElement(Wbags).click().build().perform();
		Thread.sleep(1000);
		
		//Click on five grid and Select SORT BY as "What's New"
		
		driver.findElementByXPath("//div[@class='five-grid']").click();
			
		WebElement seletdrpdwn= driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Select specific = new Select(seletdrpdwn);
		specific.selectByVisibleText("What's New");
	    Thread.sleep(3000);
	    
	    //Enter Price Range Min as 2000 and Max as 5000 
	    driver.findElementByXPath("(//span[@class='facet-left-pane-label'])[3]").click();
	    
	    driver.findElementByXPath("//input[@id='minPrice']").sendKeys("2000");
	    driver.findElementByXPath("//input[@id='maxPrice']").sendKeys("5000");
	    driver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
	    Thread.sleep(1000);
	    System.out.println("price range given");
		
	    
	    //Click on the product "Puma Ferrari LS Shoulder Bag" 
	    
	    driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
	    System.out.println("puma bag selected");
	    
	    
	    Set<String> str = driver.getWindowHandles();
		ArrayList<String> lst = new ArrayList<String>(str);
		driver.switchTo().window(lst.get(1));
		
	    String checkcoupon = driver.findElementByXPath("//div[@class='promo-desc']").getText().replaceAll("/D", "");
	    System.out.println(checkcoupon);
	    
	    // Verify the Coupon code for the price above 2690 is applicable for your product, if applicable the get the Coupon Code and Calculate the discount price for the coupon
	    
		String pricebfrdiscnt = driver.findElementByXPath("//div[@class='prod-sp']").getText().replaceAll("/D", " ").substring(4, 9);
		//int originalprice = Integer.parseInt(pricebfrdiscnt);
		System.out.println(pricebfrdiscnt);
		char[] chPrice = pricebfrdiscnt.toCharArray();
		char[] outprice = new char[chPrice.length];
		
		int count =0;
		
		for (int i = 0; i < chPrice.length; i++)
		{
			
			if(Character.isDigit(chPrice[i]))
			{
				outprice[count] = chPrice[i];
				count++;
			}
		} 
		
		String finaloutcome = new String(outprice);
		int a = Integer.parseInt(finaloutcome.trim());
		int discount = 0;
		String strcoupon = "";
	
		if(a >= 2690 )
		{
			//discount = (int)Math.round(100.0 / a * 28);
			discount = (int)Math.round(a * 28/100.0);
			strcoupon = "EPIC";
		}
		
		System.out.println("Discount amount - Your saving:"+ discount);
		
		//Check the availability of the product for pincode 560043, print the expected delivery date if it is available 
		driver.findElementByXPath("//div[@id='edd']").click();
		driver.findElementByXPath("//input[@class='edd-pincode-modal-text']").sendKeys("605008");
		driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']").click();
		
		Thread.sleep(2000);
		
		String deliverydate = driver.findElementByXPath("//ul[@class='edd-message-success-details']").getText();
		System.out.println(deliverydate);
	
	
	    // Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		 driver.findElementByXPath("//div[@class='other-info-toggle']").click();
		 System.out.println("====== *** Customer care details *** ====");
		 
		 JavascriptExecutor scrolcusadd = (JavascriptExecutor) driver;
		 scrolcusadd.executeScript("javascript:scrollBy(0,700)");
	
	     String cusaddress = driver.findElementByXPath("(//li[@class='detail-list mandatory-info'])[6]").getText();
	     System.out.println(cusaddress);
	     Thread.sleep(2000);
	     
	     //Click on ADD TO BAG and then GO TO BAG
	 
	     driver.findElementByXPath("//div[@class='gotop ic-toparw']").click();
	     Thread.sleep(2000);
	     JavascriptExecutor scroladdcart = (JavascriptExecutor) driver;
	     scroladdcart.executeScript("javascript:scrollBy(0,300)");
	     
	     driver.findElementByXPath("//span[@class='ic-pdp-add-cart']").click();
	     Thread.sleep(5000);
	     
	     
//	     WebElement closecart = driver.findElementByXPath("//div[@class='popup-blk cart-blk']");
//	     Actions bul = new Actions (driver);
//	     bul.moveToElement(closecart).perform();
	
	     WebDriverWait  wait = new WebDriverWait(driver, 30);
	     
//	     WebElement addtocart = driver.findElementByXPath("//div[@class='btn-cart']");
//	     addtocart.click();
	     wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@class='ic-pdp-add-cart']"))).click();; 
	     
	     
	     String Ototal = driver.findElementByXPath("//span[@class='price-value bold-font']").getText().replaceAll("/D", " ").substring(4, 9);
	     //int ordertotal = Integer.parseInt(Ototal);
	     System.out.println(Ototal);
//	     Double Ototal2 = Double.parseDouble(Ototal);
//	     String ordertotal = Double.toString(Ototal2);
	     
	     if(pricebfrdiscnt.equals(Ototal)) 
	     {
	    	 System.out.println("Order Total before applying coupon is correct");
	     }
	     
	     else 
	     {
	    	 System.out.println("Order Total before applying coupon not valid");
	     }
	
	     //Check the Order Total before apply coupon
	     driver.findElementByXPath("//input[@id='couponCodeInput']").sendKeys("EPIC");
	     driver.findElementByXPath("//button[@class='rilrtl-button button apply-button ']").click();
	     
	     // Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details 
	     String discountapplyamt = driver.findElementByXPath("(//span[@class='price-value discount-price'])[2]").getText().replaceAll("/D", "").substring(4, 10);	     
	     System.out.println(discountapplyamt);
	     Double finalsaveamt = Double.parseDouble(discountapplyamt);
	     int roundoff = (int) Math.round(finalsaveamt);
	     
	     if(discount == roundoff)
	     {
	    	 System.out.println("Matched EPIC coupon amount and your saving is: " + "RS:" + roundoff);
	     }
	     
	     else
	     {
	    	 System.out.println("Unmatched coupon amount ");
	    	 
	     }
	     
	//Click on Delete and Delete the item from Bag 
	     
	     driver.findElementByXPath("//div[text()='Delete']").click();
	     driver.findElementByXPath("(//div[@class='delete-btn'])[2]").click();

	//Close all the browsers
	     driver.quit();
	     
	}
	

}
