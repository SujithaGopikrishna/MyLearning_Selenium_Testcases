package makemytrip;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day3MakeMyTripWorkout {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notification");
		ChromeDriver driver = new ChromeDriver(options);
		
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		
		
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
//Click on the hotels
		driver.findElementByXPath("//span[text()='Hotels']").click();
		Thread.sleep(3000);
		
// Click on the city and enter Goa,India
		driver.findElementByXPath("//span[text()='City / Hotel / Area / Building']").click();
		//driver.findElementByXPath("//input[@class='react-autosuggest__input react-autosuggest__input--open']").sendKeys("Dubai");
		
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa");
//choose goa
		driver.findElementByXPath("(//p[@class='locusLabel appendBottom5'])[1]").click();	
		
// select the checkin date and checkout date
		driver.findElementById("checkin").click();
		//driver.findElementByXPath("(//div[@class='DayPicker-Day'])[text()='15']").click();
		
		driver.findElementByXPath("//div[@aria-label='Fri May 15 2020']").click();
		driver.findElementByXPath("//div[@aria-label='Wed May 20 2020']").click();
	
//Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		//driver.findElementById("0").click();
		//("//option[@data-cy='childAgeValue-12']"));
		
		WebElement seletdrpdwn= driver.findElementByClassName("ageSelectBox");
		Select ageselection = new Select(seletdrpdwn);
		ageselection.selectByVisibleText("12");
	
		System.out.println("2 Adults+1Children(age12) is applied");
		
		driver.findElementByXPath("//button[text()='APPLY']").click();
		
//click on search button
		
		driver.findElementById("hsw_search_button").click();
		Thread.sleep(5000);
		
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']") .click();
		
		//driver.findElementByXPath("//i[@class='we_forward']").click();
		Thread.sleep(2000);
		
		//driver.switchTo().alert().dismiss();
		//Alert alert = driver.switchTo().alert();
		//String alertmessage = driver.switchTo().alert().getText();
		//System.out.println("AlertMessage" +alertmessage);
// click the locality as baga
		//WebDriverWait  wait1 = new WebDriverWait(driver, 05);

		
		JavascriptExecutor scrolldownlocality = (JavascriptExecutor) driver;
		scrolldownlocality.executeScript("javascript:scrollBy(0,500)");
		WebElement locality = driver.findElementByXPath("(//label[@for='mmLocality_checkbox_35'])[1]");
		locality.click();
		//Actions builder = new Actions(driver);
		//builder.moveToElement(locality).perform();
		//wait1.until(ExpectedConditions.elementToBeClickable(locality)); 
		
		Thread.sleep(2000);
		// click star category as five
		//WebElement star = driver.findElementByXPath("//label[text()='5 Star']");
		//Actions builder = new Actions(driver);
		//builder.moveToElement(star);
		//builder.perform();

		JavascriptExecutor scrolldownstar = (JavascriptExecutor) driver;
		scrolldownstar.executeScript("javascript:scrollBy(0,1000)");
		driver.findElementByXPath("//label[text()='5 Star']").click();
	
// click on the first resulting hotel andgo to the new window
		
		driver.findElementByXPath("(//p[@id='hlistpg_hotel_name'])[1]").click();

//go to the new window
		Set<String> str = driver.getWindowHandles();
		ArrayList<String> lst = new ArrayList<String>(str);
		driver.switchTo().window(lst.get(1));

		//JavascriptExecutor jshotelname = (JavascriptExecutor)driver;
		//jshotelname.executeScript("javascript:scrollBy(0,500)");
		
//print hotel name
		
		WebElement HotelName= driver.findElementByXPath("(//h1[@id='detpg_hotel_name'])[1]");
		String gethotelname = HotelName.getText();
		System.out.println("Name of the first resulting hotel:"+ gethotelname);
		String pagetitle = driver.getTitle();
		System.out.println("To confirm the first resulting hotel name with page titel:" +pagetitle);
		
// move to more options
		//driver.findElementByXPath("(//span[text()='MORE OPTIONS'])[1]").click();
		
		driver.findElementById("detpg_multi_view_combo").click();
		Thread.sleep(3000);
		//  select the 3 months plan and close it
		//driver.findElementByXPath("(//span[@class='latoBold font12 pointer makeFlex hrtlCenter right blueText'])[1]").click();
		//driver.findElementByXPath("//span[@class='close']").click();
		

              try {
                 driver.findElementByPartialLinkText("Alert:").click();
                  }
                 catch (Exception ex)
                 {
	
                   System.out.println("alret is not handled");
	 
	           ex.printStackTrace();
	
                   }
		
	
		//driver.switchTo().frame(driver.findElementByXPath("//div[@class='tablerow']"));
		//driver.findElementByClassName("we_forward").click();
		
		//driver.switchTo().defaultContent();
		//Thread.sleep(2000);
		
		driver.findElementById("detpg_book_combo_btn").click();
		String paymentamount = driver.findElementById("totalPaymentRow").getText();
		System.out.println("Total payment amount for the trip:" +paymentamount);

	}

}
