package nykaa;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class NykaaTestCase {

	public static void main(String[] args) throws InterruptedException {
	
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver =new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Move to the element "BRAND"
		WebElement movebrand  = driver.findElementByXPath("//a[text()='brands']");
		Actions builder = new Actions(driver);
		builder.moveToElement(movebrand).perform();
		
		//Move to the element "POPULAR"
		WebElement movepopular = driver.findElementByXPath("//a[text()='Popular']");
		Actions builderpopular = new Actions(driver);
		builderpopular.moveToElement(movepopular).perform();
		
		//Click on the Loreal Paris product
	    WebElement currentwindow = driver.findElementByXPath("//a[@href='/brands/loreal-paris/c/595?eq=desktop']");
	    currentwindow.click();
	    Set<String> currentpage = driver.getWindowHandles();
	    ArrayList<String> Winhan = new ArrayList<String>(currentpage);
	    driver.switchTo().window(Winhan.get(1));
	    
		// Get the title of the Secondpage
		String title= driver.getTitle();
		System.out.println(title);
		
		// Check the secondwindow title contain the "L'Oreal Paris"
		if (title.contains("Oreal"))
		{
			System.out.println("Title matches with searched product");
		}
		else
		{
			System.out.println("Title does not matches with searched product");
		}
		
       //Click option "sortby" and select customor top rated
		driver.findElementByXPath("//span[text()='Sort By : ']").click();
		driver.findElementByXPath("(//div[@class='control__indicator radio'])[4]").click();
		
		//Click "Catogery" option and click shampoo
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("(//div[@class='control__indicator'])[31]").click();
		
		//To check the filter is applied for "Shampoo"

		String confirmfilter = driver.findElementByXPath("//li[text()='Shampoo']").getText();
		System.out.println(confirmfilter);
		
		if (confirmfilter.contains("Shampoo"))
		{
			System.out.println("Filter applied is correct for the customer choice - shampoo");
		}
		else 
		{
			System.out.println("Filter applied is not matched with the customer choice");
		}
		Thread.sleep(3000);
		
		// click on the "L'Oreal Paris Colour Protect Shampoo"
		
		//driver.findElementByXPath("//span[text()='L'Oreal Paris Colour Protect Shampoo']").click();
		
		
		//*[@id="listingContainer"]/div[3]/div/div/div[2]/div/div/div[4]/div/a
		
		driver.findElementByXPath("(//div[@class='m-content__product-list__title'])[4]//span").click();
		
		//div[@class='m-content__product-list__title'])[4]//span
		
		// Switch to new window and select the size 175 ml
		Set<String> cuschoicewindow = driver.getWindowHandles();
		ArrayList <String> cusproductselect = new ArrayList<String>(cuschoicewindow);
		driver.switchTo().window(cusproductselect.get(2));
		
		
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='175ml']").click();
		
		// print the MRP of the product
		
		//WebElement price = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]");
		WebElement price = driver.findElementByXPath("(//span[text()='150'])[1]");
		String printprice = price.getText();
		System.out.println("Printing the price of select product:" +printprice.replaceAll("//D", "")); 
	
		// select the option 175ml from dropdown
		
		Thread.sleep(2000);
//		Select ML = new Select(driver.findElementByXPath("(//div[@class='select-style shade-select'])[1]"));
//		ML.selectByVisibleText("175ml");
		
		
		driver.findElementByXPath("(//button[text()='ADD TO BAG'])[1]").click();
		
		// Go to bag icon
		
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		
		//print the grandtotal
		WebElement WebGrandTotal = driver.findElementByXPath("//div[@class='value medium-strong']");
		String PrintGranTotal = WebGrandTotal.getText();
		System.out.println("Grand Total of Product:"+ PrintGranTotal.replaceAll("//D", ""));
		
		// Click on the proceed
		
		driver.findElementByXPath("//span[text()='Proceed']").click();
		
		// Click on the button continue as guest
		
		driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
		
		// Print the warning message
		
		WebElement warning = driver.findElementByXPath("//div[text()='Please expect delay in shipments because of the national lockdown']");
                String Printwarning = warning.getText();
                System.out.println("Warning regarding current situation: "+ Printwarning);
        
               // close all the windows
               driver.quit();
        
        
		
	} 

}
