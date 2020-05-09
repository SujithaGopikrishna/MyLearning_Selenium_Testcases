package microsoft;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class MicrosoftTestCase {
	
    public static void generateExcel(String flag, ChromeDriver driver) throws IOException, InterruptedException 
    {
    	String downloadPath = "C:\\Users\\Dell\\Downloads";
		String fileName = "ExportedEstimate";
		String format = ".xlsx";
		File Dir = new File(downloadPath);
    	for (File f : Dir.listFiles()) 
    	{
    	    if (f.getName().startsWith(fileName)) 
    	    {
    	        f.delete();
    	    }
    	}  	
    	driver.findElementByXPath("//button[text()='Export']").click();
    	Thread.sleep(5000);
    	
    	// Verify the downloded file in the local folder
		boolean check = new File(downloadPath, fileName + format).exists();
		if(check) 
		{
			for (File f : Dir.listFiles()) 
	    	{
	    	    if (f.getName().startsWith(fileName)) 
	    	    {
	    	      f.renameTo(new File(downloadPath, flag + "_" + fileName + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + format));
	    	    }
	    	} 
	        System.out.println(flag + " " +  fileName + " " + "Excel file exists in local path!");
		}
		else
		{
			System.out.println(fileName + " " + "Excel file not generated!");
		}
 }
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(option);
		System.setProperty("webdriver.chrome.silentouput", "true");
		System.setProperty("safebrowsing.enable", "true");
		
		//Go to https://azure.microsoft.com/en-in/ 
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		//Click on Pricing
		driver.findElementByXPath("//a[@id='navigation-pricing']").click();
		
		
		//Click on Pricing Calculator  //Pricing calculator
		driver.findElementByLinkText("Pricing calculator").click();
		Thread.sleep(2000);
		
		//Click on Containers 
		JavascriptExecutor scrolFcontainer = (JavascriptExecutor) driver;
		scrolFcontainer.executeScript("javascript:scrollBy(0,500)");
		
		driver.findElementByXPath("//button[@value='containers']").click();
		Thread.sleep(3000);
		System.out.println("Container is selected");
				
        //Select Container Instances 
		driver.findElementByXPath("(//span[@class='sd-truncateText'])[56]").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[text()='View']"))).click();
				
		//Select Region as "South India" 
		WebElement countrylst = driver.findElementByXPath("(//select[@class='select'])[1]");
		Select dropdwn = new Select(countrylst);
		dropdwn.selectByVisibleText("South India");
		System.out.println("Country selected as South India");
		
		//Set the Duration as 180000 seconds 
		WebElement duration = driver.findElementByXPath("//input[@aria-label='Seconds']");
		//duration.clear();
		//duration.sendKeys("80000");
		
		duration.sendKeys(Keys.CONTROL + "a");
		duration.sendKeys(Keys.DELETE);
		duration.sendKeys("180000");
		
		for(int i =1; i<= 6; i++)
		{
		duration.sendKeys(Keys.ARROW_LEFT);
		}
		
		duration.sendKeys(Keys.BACK_SPACE);
		
		for(int i =1; i<= 6; i++)
		{
		duration.sendKeys(Keys.ARROW_RIGHT);
		}
				
		//Select the Memory as 4GB 
		WebElement memory = driver.findElementByXPath("//select[@name='memory']");
		Select memoryDD = new Select(memory);
		memoryDD.selectByVisibleText("4 GB");
		
		// Enable SHOW DEV/TEST PRICING 
		JavascriptExecutor js =  (JavascriptExecutor)  driver; 
		js.executeScript("javascript:scrollBy(0,700)");
		
		driver.findElementByXPath("//button[@id='devtest-toggler']").click();
		Thread.sleep(5000);
		
		//Select Indian Rupee  as currency
		WebElement currency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select currencyDD = new Select(currency);
		//currencyDD.selectByVisibleText("Indian Rupee");
		currencyDD.selectByValue("INR");
		
		//Print the Estimated monthly price
		String priceamount = driver.findElementByXPath("(//span[@class='numeric'])[4]").getText();
		System.out.println("Estimated Monthly cost:"+ priceamount);
		
		//Click on Export to download the estimate as excel 
		generateExcel("Manual", driver);
		
		//Navigate to Example Scenarios and Select CI/CD for Containers
		JavascriptExecutor scrollup =  (JavascriptExecutor)  driver; 
		scrollup.executeScript("javascript:scrollBy(0,-1000)");
		
		WebElement eg =  driver.findElementByXPath("//a[text()='Example Scenarios']");
		Actions builder = new Actions(driver);	
		
		builder.moveToElement(eg).click().perform();
		
		JavascriptExecutor scrolCICD =  (JavascriptExecutor)  driver; 
		scrolCICD.executeScript("javascript:scrollBy(0,400)");
		
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();
		System.out.println("CI/CD for Containers is selected");
		
		//Click Add to Estimate
		JavascriptExecutor scrolestimate =  (JavascriptExecutor)  driver; 
		scrolestimate.executeScript("javascript:scrollBy(0,200)");
		
		driver.findElementByXPath("//button[@class='button button--secondary01 pull-right']").click();
		Thread.sleep(5000);
		
		//Change the Currency as Indian Rupee
		JavascriptExecutor scrolcurrency =  (JavascriptExecutor)  driver; 
		scrolcurrency.executeScript("javascript:scrollBy(0,1000)");
		
		WebElement currency2 = driver.findElementByXPath("//Select[@class='select currency-dropdown']");
		Select cur2 = new Select(currency2);
		cur2.selectByValue("INR");
		
		//Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//button[@id='devtest-toggler']").click();
		
		//Export the Estimate 
		generateExcel("CICD", driver);
		
	}
	
}
