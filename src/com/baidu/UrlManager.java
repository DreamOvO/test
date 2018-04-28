package com.baidu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UrlManager {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getProperties().setProperty("webdriver.chrome.driver","E:\\Workspace\\Spider\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		work(webDriver);
		webDriver.close();
	}
	
	public static void work(WebDriver webDriver) {
		String urlStr="http://top.baidu.com/buzz?b=42&fr=topbuzz_b341";
		webDriver.manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
		//打开url
		webDriver.get(urlStr);
		
		//获取多个热搜榜
        List<WebElement> items=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<List<WebElement>>() {
			
			@Override
			public List <WebElement> apply(WebDriver driver) {
				return driver.findElements(By.xpath("//div[@class='hblock']/ul/li/a"));
				 //WebElement t = webDriver.findElement(By.cssSelector("div.channel"));
			      // List<WebElement> titles=t.findElements(By.tagName("a"));
			      // return titles;
			}
		});
        items.remove(0);
        List <String> listURL=new ArrayList<String>();
        //获取热搜榜地址
        for(WebElement item:items) {
			System.out.println(item.getAttribute("href"));
			listURL.add(item.getAttribute("href"));
		} 
        
        
        //获取热搜词
        for(String item:listURL) {
        	//work(webDriver ,item);
    		webDriver.get(item);
            items=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<List<WebElement>>() {
    			
    			@Override
    			public List <WebElement> apply(WebDriver driver) {
    				return driver.findElements(By.xpath("//table[@class='list-table']/tbody/tr/td[@class='keyword']/a"));
    				 //WebElement t = webDriver.findElement(By.cssSelector("div.channel"));
    			      // List<WebElement> titles=t.findElements(By.tagName("a"));
    			      // return titles;
    			}
    		});
            //输出热搜词
            for(WebElement t:items) {
    			System.out.println(t.getText());
    		} 
     	} 
		
	}
}
