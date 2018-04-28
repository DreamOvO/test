package com.download;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SohuIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getProperties().setProperty("webdriver.chrome.driver","E:\\Workspace\\Spider\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		work(webDriver);
		webDriver.close();
	}
	
	public static void work(WebDriver webDriver) {
		String urlStr="http://zhishu.sogou.com/index/searchHeat?kwdNamesStr=计算机&timePeriodType=WEEK&dataType=SEARCH_ALL&queryType=INPUT";
		webDriver.manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
		//打开url
		webDriver.get(urlStr);
		WebElement keyword =new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@class='sinp']/input"));
			}
		}); 
        //enter keyword
		keyword.clear();
        keyword.sendKeys("霍金");
        //get search button and click it
        WebElement searchBtn = new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@class='s-box']/a"));
			}
		}); 
        		
        searchBtn.click();
        
        //获取搜索参数
        List<WebElement> indextds = new WebDriverWait(webDriver, 30).until(new ExpectedCondition<List<WebElement>>() {
			
			@Override
			public List <WebElement> apply(WebDriver driver) {
				return driver.findElements(By.xpath("//table/tr"));
			}
		});
        
      //  List<WebElement> indextds = webDriver.findElements(By.tagName("tr"));        
        System.out.println(indextds.size());
        for(WebElement ind:indextds) {
        	
        	System.out.print(ind.getText()+"  ");
        	System.out.println();
        }
        	
        
  
		
	}

}
