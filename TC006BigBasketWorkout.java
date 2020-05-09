package TC006BigBasket;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC006BigBasketWorkout {

	public static void main(String[] args) throws InterruptedException {
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		DesiredCapabilities capba = new DesiredCapabilities();
		capba.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR , UnexpectedAlertBehaviour.DISMISS);
		capba.merge(options);
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentouput", "true");
			
		
		//Launch the URL: BigBasket Website
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		//Mouse over on the shop on category
		Actions buildercategory = new Actions(driver);
		WebElement category = driver.findElementByXPath("//a[text()=' Shop by Category ']");
		buildercategory.moveToElement(category).perform();
		Thread.sleep(2000);
		
		//Go to FOOD,GRAINS AND MASALA --> Rice,   //(//li[@data-submenu-id='foodgrains-oil-masala'])[1]/parent::ul ;;;;; //(//a[text()='Foodgrains, Oil & Masala'])[1]
		
		WebElement grains = driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]");
		buildercategory.moveToElement(grains).perform();
		System.out.println("foodgrain is selected");
		Thread.sleep(2000);
		
		
		buildercategory.moveToElement(driver.findElementByXPath("(//a[text()='Rice & Rice Products'])[2]")).perform();
		
		System.out.println("Rice and Rice product is selected");
		
		//Click on the Boiled and Steamed rice
		
		driver.findElementByXPath("(//a[text()='Boiled & Steam Rice'])[2]").click();
		Thread.sleep(2000);
		// Choose the brand bb Royal
		
		driver.findElementByXPath("(//span[text()='bb Royal'])[1]").click();
	    System.out.println("Brand - bb Royal is choosed");
		Thread.sleep(2000);
	    // Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
	    
	    Actions specificbrand = new Actions(driver);
	    specificbrand.moveToElement(driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']")).perform();
	    Thread.sleep(2000);
	    driver.findElementByXPath("(//button[@data-toggle='dropdown'])[4]").click();
	    
	    
	    driver.findElementByXPath("(//ul[@class='dropdown-menu drop-select']//a)[9]").click();
	    Thread.sleep(1000);
	    System.out.println("5kg is choosed");
	    //li[@='allProducts in product.all_prods ']
	    
	    //Get the price of the product //(//div[@qa='price']//h4)[3]
	    
	    String Getprice = driver.findElementByXPath("(//span[@class='discnt-price'])[3]").getText().replaceAll("/D", "");
	    System.out.println("Price of the selected Rice product:"+Getprice);
	    Thread.sleep(2000);
	    
	    // Click on the "ADD"   //button[text()='Add '])[3]/parent::div
	    
	    driver.findElementByXPath("(//button[@qa='add'])[3]//parent::div").click();
	    Thread.sleep(2000);
	    
	    
	    
	    try {
			driver.findElementByLinkText("Continue").click();
		} catch (Exception e) {
			System.out.println("Product delivery location detailes is not displayed ");
			e.printStackTrace();
		}
		

	    
	    
	    // (//p[text()='You are viewing our product catalogue in'])[1]  //
	    
	    //String t1 = driver.findElementByXPath("(//p[text()='You are viewing our product catalogue in'])[1]//parent::div").getText();
	    //System.out.println(t1);
	    // (//p[text()=' to view your saved addresses']/a)[1] 
//	    String t2= driver.findElementByXPath(" (//p[text()=' to view your saved addresses'])[1]").getText();
//	    System.out.println(t2);
	  //span[@class='arrow-marker']
	   // Thread.sleep(2000);
	   // WebElement locationpopup = driver.findElementByXPath("//span[@class='hvc']");
	   // Actions builderpopup = new Actions(driver);
	   // builderpopup.moveToElement(locationpopup);
	    // driver.findElementByXPath("//span[@class='arrow-marker']").click();
	    //Thread.sleep(2000);
	    
	    driver.findElementByXPath("//input[@id='input']").sendKeys("dal");
	    System.out.println("dal is searched");
	    
	    driver.findElementByXPath("//button[@class='btn btn-default bb-search']").click();
	    Thread.sleep(2000);
	    
	    JavascriptExecutor js= (JavascriptExecutor) driver;
	    js.executeScript("javascript:scrollBy(0,300)");
	    
	    WebElement toordal = driver.findElementByXPath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']");
	    Actions buildertoordal = new Actions(driver);
	    buildertoordal.moveToElement(toordal).perform();
	    Thread.sleep(2000);
	    	    
	    WebElement quantity = driver.findElementByXPath("(//input[@ng-model='vm.startQuantity'])[2]");
	    		quantity.clear();
	    		quantity.sendKeys("2");
	    driver.findElementByXPath("(//button[@class='btn btn-default dropdown-toggle form-control'])[2]").click();
	    
	    
	    //li[@ng-repeat='allProducts in product.all_prods '])[6]/parent::ul
	    driver.findElementByXPath("(//li[@ng-repeat='allProducts in product.all_prods '])[5]").click();
	    
	    //print the price of the rice
	    String getprice = driver.findElementByXPath("(//span[@class='discnt-price'])[2]").getText();
	    System.out.println("Print the price of rice:" + getprice);
	    Thread.sleep(1000);
	    
	    // Click on the Add button
	    driver.findElementByXPath("(//button[@qa='add'])[2]").click();

	    try {
			driver.findElementByPartialLinkText("Successfully added Toor").click();
		} catch (Exception excep) {
			System.out.println("selected product is not successfully added to the cart ");
			excep.printStackTrace();
		}
		
	    Thread.sleep(2000);
	    
	    
	    
	   // driver.findElementByPartialLinkText("Successfully").click();
	    
	    //Mouse over My Basket
	    WebElement mybasket = driver.findElementByXPath("//span[text()='My Basket']");
	    Actions builderbasket = new Actions(driver);
	    builderbasket.moveToElement(mybasket).perform();
	    System.out.println("mouse over");
	    
	    Thread.sleep(2000);
	    String cartone = driver.findElementByXPath("(//span[@qa='priceMB'])[1]").getText().replaceAll("/D", " ");
	    System.out.println(cartone);
	    
	    String cartsecond = driver.findElementByXPath("(//span[@qa='priceMB'])[2]").getText().replaceAll("/D"," ");
	    System.out.println(cartsecond);
	    
	    String totalprice = driver.findElementByXPath("//span[@qa='subTotalMB']").getText();
	    System.out.println(totalprice);
	    double finalprice = Double.parseDouble(totalprice);
	    System.out.println(finalprice);
	    Thread.sleep(2000);
    
	     Double sumoftwoitems = Double.parseDouble(cartone) + Double.parseDouble(cartsecond);
	     System.out.println(sumoftwoitems);
	    
         if (finalprice == sumoftwoitems)
           {
   	    	System.out.println("Sum of all items price is correct");
	       }
         
         else
         {
        	 System.out.println("total sum price get differ");  	 
         }
	    
         driver.findElementByXPath("(//button[@qa='decQtyMB'])[2]").click();
         Thread.sleep(1000);
    
         String cartsecond2 = driver.findElementByXPath("(//span[@qa='priceMB'])[2]").getText().replaceAll("/D"," ");
 	     System.out.println(cartsecond2);
 	    
 	     Double sumoftwoitems2 = Double.parseDouble(cartone) + Double.parseDouble(cartsecond2);
	     System.out.println(sumoftwoitems2);
 	    
	     Thread.sleep(1000);
	     String totalprice2 = driver.findElementByXPath("//span[@qa='subTotalMB']").getText();
 	     double finalprice2 = Double.parseDouble(totalprice2);
 	     System.out.println(finalprice2);
 	     Thread.sleep(2000);
 	    
 	      if (finalprice2 == sumoftwoitems2)
           {
	    	System.out.println("Sum of all items price is correct");
	       }
      
           else
           {
     	    System.out.println("total sum price get differ");  	 
           }
 	      
 	      driver.quit();
	}

}
