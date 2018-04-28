package com.URLManage;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {
/****
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 System.getProperties().setProperty("webdriver.chrome.driver","E:\\Workspace\\Spider\\chromedriver.exe");
//		//System.getProperties().setProperty("webdriver.chrome.driver","/Users/yihua/Downloads/chromedriver");
		WebDriver webDriver = new ChromeDriver();
		webDriver.get("http://www.baidu.com/");
		//WebElement webElement = webDriver.findElement(By.xpath("/html"));
		//System.out.println(webElement.getAttribute("outerHTML"));
		//webDriver.close();
		
		
		WebElement keyword = webDriver.findElement(By.id("kw"));
        //enter keyword
        keyword.sendKeys("selenium");
        //get search button and click it
        WebElement searchBtn = webDriver.findElement(By.id("su"));
        searchBtn.click();
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//waiting for the result
        //get result and print out cyclical--搜索结果是h3标签class=“t”，故以此为条件
        List<WebElement> titles = webDriver.findElements(By.cssSelector("h3.t"));
        for(WebElement title:titles){
            WebElement webTitle=title.findElement(By.tagName("a"));
            //System.out.println(webTitle.getText());
            System.out.println(webTitle.getAttribute("href"));
            //System.err.println("webTitle:"+webTitle+":"+webTitle.getText());
        }
        
        webDriver.close();

    }
		***/
	}


