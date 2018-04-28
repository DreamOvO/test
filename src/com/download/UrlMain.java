package com.download;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UrlMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 System.getProperties().setProperty("webdriver.chrome.driver","E:\\Workspace\\Spider\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get("http://www.toutiao.com/");
//       try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e) {
//			
//			e.printStackTrace();
//		}
       WebElement t = webDriver.findElement(By.cssSelector("div.channel"));
       List<WebElement> titles=t.findElements(By.tagName("a"));
       List<String>URLlist=new ArrayList<String>();
       for(WebElement title:titles){
          
    	   URLlist.add(title.getAttribute("href"));
           
       }
       
		
		
       webDriver.get(URLlist.get(9));
       
       List<WebElement> t1 = webDriver.findElements(By.cssSelector("li.item"));
    //   t1.remove(t1.size()-1);
//       for(WebElement title:t1){
//           WebElement tt=title.findElement(By.tagName("a"));
//    	   System.out.println(tt.getAttribute("href"));
//           
//       }
       WebElement tt1 =t1.get(3).findElement(By.tagName("a"));
       System.out.println(tt1.getAttribute("href"));
		 webDriver.get(tt1.getAttribute("href"));
		 WebElement t2 = webDriver.findElement(By.cssSelector("div.article-box"));
		 System.out.println(t2.getText());
		

       webDriver.close();
	}

}
