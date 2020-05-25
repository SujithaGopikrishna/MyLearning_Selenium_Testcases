package TC007Honda;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class HondaTestCase {

	public static void main(String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(option);
		System.setProperty("silentoutput","SILENT");
		
		// Go to https://www.honda2wheelersindia.com/ 
		driver.get(" https://www.honda2wheelersindia.com/");
		driver.manage().window().maximize();
		
		//close the precatuion alert popup
		driver.findElementByXPath("//button[@class='close']").click();
		
		 // Click on scooters and click dio
		driver.findElementByLinkText("Scooter").click();
		Thread.sleep(1000);
		driver.findElementByXPath("(//img[@src='/assets/images/thumb/dioBS6-icon.png'])[1]").click();
		System.out.println("Dio is selected");
		
		//Click on Specifications and mouseover on ENGINE 
		
		driver.findElementByXPath("//a[text()='Specifications']").click();
		Thread.sleep(2000);
		WebElement engine = driver.findElementByXPath("//a[@name='2']");
		Actions builderengine = new Actions(driver);
		builderengine.moveToElement(engine).perform();
		Thread.sleep(2000);
		List<WebElement> enginespecific = driver.findElementsByXPath("(//div[@class='engine part-2 axx']//li//span)[4]");
		
		for (WebElement list : enginespecific) 
		{
			System.out.println(list.getText());
		}
		
		String diodisplmntvalue = driver.findElementByXPath("(//div[@class='engine part-2 axx']//li//span)[5]").getText();
		System.out.println("Displacement value:" + diodisplmntvalue);
		double diovalue = Double.parseDouble(diodisplmntvalue.substring(0, 5));
		System.out.println(diovalue);
		
		String diodisplacement = driver.findElementByXPath("(//div[@class='engine part-2 axx']//li//span)[4]").getText();

		System.out.println(diodisplacement + " " + diodisplmntvalue);
		
		// Go to Scooters and click Activa 125
			
		driver.findElementByLinkText("Scooter").click();
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();
		
		System.out.println("Activa 125 is selected");
		Thread.sleep(1000);
		
		//Click on Specifications and mouseover on ENGINE
		
		driver.findElementByLinkText("Specifications").click();
		Thread.sleep(2000);
		WebElement ActivaEngine = driver.findElementByXPath("//a[@name='4']");
		Actions builderdActivaEngine = new Actions(driver);
		builderdActivaEngine.moveToElement(ActivaEngine).perform();
		
		
		//Get Displacement value
		
		String Activadisplmntvalue = driver.findElementByXPath("(//div[@class='engine part-4 axx']//li//span)[5]").getText();
		System.out.println("Displacement value:" + Activadisplmntvalue);
		int Activavalue = Integer.parseInt(Activadisplmntvalue.substring(0, 3));
		System.out.println(Activavalue);
		
		String Activadisplacement = driver.findElementByXPath("(//div[@class='engine part-4 axx']//li//span)[4]").getText();

		System.out.println(Activadisplacement + " " + Activadisplmntvalue);
		
		
		//Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		if(diovalue > Activavalue)
		{
			System.out.println("Scooter:Dio having high displacement value");
		}
		
		else
		{
			System.out.println("Scooter:Activa125 is having high displacement value");
		}
		
		//Click FAQ from Menu 
		
		driver.findElementByXPath("(//a[text()='FAQ'])[1]").click();
		Thread.sleep(2000);
		
		//Click Activa 125 BS-VI under Browse By Product
		driver.findElementByXPath("//a[text()='Activa 125 BS-VI']").click();
		
		//Click  Vehicle Price 
		driver.findElementByXPath("//a[text()=' Vehicle Price']").click();
		
		//Make sure Activa 125 BS-VI selected and click submit 
		
		WebElement VT = driver.findElementById("SegmentID6");
		Select ss = new Select(VT);
		String getVT = ss.getFirstSelectedOption().getText();
		System.out.println(getVT);
		
		
		WebElement vehicalname = driver.findElementByXPath("//select[@id='ModelID6']");
		Select VN = new Select(vehicalname);
		String getVN = VN.getFirstSelectedOption().getText();
		System.out.println(getVN);
		Thread.sleep(2000);
		
		String type ="Scooter";
		String Name = "Activa 125 BS-VI";
               if ((getVT.equals(type)) && (getVN.equals(Name)))
		{
			driver.findElementByXPath("//button[@id='submit6']").click();
			System.out.println(" *****checking for price value ****"+ "\n" +"Click the link to view price details");
		}
                 else
                {
        	System.out.println("Vehical Type and Name is not matched");
                }
	     Thread.sleep(2000);
	     System.out.print("\n");
       
        //click the price link 
        String gettabletext = driver.findElementByXPath("(//table[@id='tblPriceMasterFilters'])/tbody/tr/td[3]").getText();
        
       
        System.out.println(gettabletext);
        
        driver.findElementByXPath("//table[@id='tblPriceMasterFilters']/tbody/tr[1]/td[3]").click();
        
        //Go to the new Window and select the state as Tamil Nadu and  city as Chennai   
            
        Set<String> str = driver.getWindowHandles();
		ArrayList<String> lst = new ArrayList<String>(str);
		driver.switchTo().window(lst.get(1));
        
        WebElement state = driver.findElementByXPath("//select[@id='StateID']");
        Select Sstate = new Select(state);
        Sstate.selectByVisibleText("Tamil Nadu");
        
        WebElement city = driver.findElementByXPath("//select[@id='CityID']");
        Select Scity = new Select(city);
        Scity.selectByVisibleText("Chennai");
        
        Thread.sleep(1000);      
        driver.findElementByXPath("//button[text()='Search']").click();
        Thread.sleep(2000);
        
        System.out.println(" ****** selected state - Tamil Nadu & city - Chennai ****** ");
       
        System.out.print("\n");
        
        System.out.println(" ****** List od Model and Prices ****** ");
       
        String get1stmodel = driver.findElementByXPath("(//table[@id='gvshow']//tbody//tr//td[2])[1]").getText();
        String get1stprice = driver.findElementByXPath("(//table[@id='gvshow']//tbody//tr//td[3])[1]").getText();
        System.out.println(get1stmodel + " - " + get1stprice);
        
        String get2ndmodel = driver.findElementByXPath("(//table[@id='gvshow']//tbody//tr//td[1])[2]").getText();
        String get2ndprice = driver.findElementByXPath("(//table[@id='gvshow']//tbody//tr//td[2])[2]").getText();
        System.out.println(get2ndmodel + " - " + get2ndprice);
        
        String get3rdmodel = driver.findElementByXPath("(//table[@id='gvshow']//tbody//tr//td[1])[3]").getText();
        String get3rdprice = driver.findElementByXPath("(//table[@id='gvshow']//tbody//tr//td[2])[3]").getText();
        System.out.println(get3rdmodel + " - " + get3rdprice);
        
        
        driver.quit();
	}
	

}
