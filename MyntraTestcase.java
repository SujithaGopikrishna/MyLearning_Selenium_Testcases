package myntra;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class MyntraTestcase {

	public static void main(String[] args) throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "./drivers./chromedriver.exe");
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://www.myntra.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	//Move to the element "Women"
	WebElement womenover = driver.findElementByXPath("//a[text()='Women']");
	Actions builder =new Actions(driver);
	builder.moveToElement(womenover).perform();
	Thread.sleep(3000);
	
	//From the displayed items click on the "Jacket and Coats"
	driver.findElementByXPath("//a[text()='Jackets & Coats'][1]").click();
	
	//To get total count Jackets and Coats
	String text = driver.findElementByXPath("//span[@class='title-count']").getText();
	
	//To remove all unwanted space and other items
	String tltcount = text.replaceAll("\\D","");
	
	//convert string to integer
	int jckandcotcount = Integer.parseInt(tltcount);
	
	//Printing the total count of Jackandcoat count
	System.out.println("Total amount of JacketsAndCoats:" +jckandcotcount);
	
	//Finding and printing the jacketcount alone
	String first = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
	String firstcount = first.replaceAll("\\D","");
	int jacketcount = Integer.parseInt(firstcount);
	System.out.println("Jacketcount:" +  jacketcount);
	
	//Finding the count of coat alone
	
	String second = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
        String secondcount = second.replaceAll("\\D", "");
        int coatcount = Integer.parseInt(secondcount);
        System.out.println("CoatCount:" + coatcount);
	
	
	int totalsum = jacketcount + coatcount;
	
        System.out.println("Sum of jackets and coats:" + totalsum );
   
   if(totalsum == jckandcotcount)
   {
	   System.out.println("Sum of jacket and coat is equal to total count of Coats & Jackets For Women");
   }
   else
   {
	  System.out.println(" sum is not equal to totalcount");
   }
	
   
   //checking the coat checkbox
   driver.findElementByXPath("(//label[@class='common-customCheckbox vertical-filters-label'])[2]").click();
  
   // to expand more options
   driver.findElementByXPath("//div[@class='brand-more']").click();
  
   //search for mango option in the search box
   driver.findElementByXPath("//input[@class='FilterDirectory-searchInput']").sendKeys("MANGO");
   Thread.sleep(2000);
  
   
   driver.findElementByXPath("//span[@class='FilterDirectory-count']/following-sibling::div[1]").click();
   
   // To close the Mango option Tab
   driver.findElementByXPath("(//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove'])[1]").click();
   Thread.sleep(3000);
   
   // to confirm all the brands are of "Mango"
   List<WebElement> brand = driver.findElementsByXPath("//h3[text()='MANGO']");
   
   int totalmangobrandsize = brand.size();
   System.out.println("Total no of Mango brand available:"+ totalmangobrandsize);
   int count=0;
   for(WebElement eachbrand : brand) 
   {
	   
	   if(eachbrand.getText().equals("MANGO")) 
	   {
		   count = count +1;
		   System.out.println("All brand types are Matches with MANGO");
		   System.out.println(count);
	    }
	   else
	   {
		   System.out.println("some of the brand which are not matches with mango");
	   }	 
   }
   
   
   WebElement recommended= driver.findElementByXPath("//span[@class='myntraweb-sprite sort-downArrow sprites-downArrow']");
   Actions discountbuilder = new Actions(driver);
   discountbuilder.moveToElement(recommended).perform();
   
   driver.findElementByXPath("//label[text()='Better Discount']").click();
   Thread.sleep(1000);
   
   List<WebElement> firstprice = driver.findElementsByXPath("//span[@class='product-discountedPrice']"); 
  String getfirstpricetext = firstprice.get(0).getText().replaceAll("/D", "");
  System.out.println(getfirstpricetext);
  
  // Mouse over on size of the first item
  
  WebElement productsize = driver.findElementByXPath("(//h3[@class='product-brand'])[1]");
  Actions builderproduct = new Actions(driver);
  builderproduct.moveToElement(productsize).perform();
  
//  Click on the wishlist
  driver.findElementByXPath("//span[@class='product-actionsButton product-wishlist ']").click();
 //  Close Browser
  driver.close();
     
   }
}
