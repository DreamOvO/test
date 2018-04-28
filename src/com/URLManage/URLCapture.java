package com.URLManage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class URLCapture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getProperties().setProperty("webdriver.chrome.driver","E:\\Workspace\\Spider\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		work(webDriver);
		webDriver.close();
	}
	
	public static void work(WebDriver webDriver) {
		//url地址
		String urlStr="http://www.toutiao.com/";
		webDriver.manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
		//打开url
		webDriver.get(urlStr);
		//等待页面刷新
		List<WebElement> items=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<List<WebElement>>() {
			
			@Override
			public List <WebElement> apply(WebDriver driver) {
				//return driver.findElements(By.xpath("//div[@class='channel']/ul/li/a"));
				 WebElement t = webDriver.findElement(By.cssSelector("div.channel"));
			       List<WebElement> titles=t.findElements(By.tagName("a"));
			       return titles;
			}
		});
		//items.remove(2);
		items.remove(items.size()-1);
		//List<WebElement> itemUrl=new ArrayList<WebElement>();
		for(WebElement item:items) {
			System.out.println(item.getAttribute("href"));
		}
		
		
		//定位文档
		webDriver.get(items.get(6).getAttribute("href"));
		List<WebElement> itemUrl= new WebDriverWait(webDriver, 30).until(new ExpectedCondition<List<WebElement>>() {
			
			@Override
			public List <WebElement> apply(WebDriver driver) {
				return driver.findElements(By.xpath("//div[@class='title-box']/a"));
			}
		});
         
         
         for(WebElement item:itemUrl) {
 			System.out.println(item.getAttribute("href"));
 		}
		
         
         //下载文档
        // WebElement tt1 =.get(3).findElement(By.tagName("a"));
         System.out.println(itemUrl.get(0).getAttribute("href"));
         webDriver.get(itemUrl.get(0).getAttribute("href"));
		 WebElement t2 = webDriver.findElement(By.cssSelector("div.article-box"));
		 System.out.println(t2.getText());
	}

}
