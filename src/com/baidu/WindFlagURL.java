package com.baidu;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindFlagURL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getProperties().setProperty("webdriver.chrome.driver","E:\\Workspace\\Spider\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		work(webDriver);
		webDriver.close();
	}
	public static void work(WebDriver webDriver) {
		String urlStr="http://top.baidu.com/region?fr=toppopulation";
		webDriver.manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
		//��url
		webDriver.get(urlStr);
		
		//��ȡ������Ѱ�
        List<WebElement> items=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<List<WebElement>>() {
			
			@Override
			public List <WebElement> apply(WebDriver driver) {
				return driver.findElements(By.xpath("//div[@class='tab-hd tab-skin-main tab-skin-main-fixed']/ul/li"));
		
			}
		});
        //���ѡ��ͬ����ķ����
       // List<WebElement> kwItems=null;
       // for(WebElement item:items) {
            WebElement item=items.get(3);
        	item.click();
        	System.out.println("��ȡ "+item.getText()+" URL");
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	List<WebElement> kwItems=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<List<WebElement>>() {
     			
     			@Override
     			public List <WebElement> apply(WebDriver driver) {
     				return driver.findElements(By.xpath("//div[@class='item-list-wrap']/ul[@class='item-list']/li/div[@class='item-hd']/a[@class='list-title']"));
     		
     			}
     		});
        	
        	System.out.println(kwItems.size());
        	//kwItems=null;
        //��ȡ��ͬ��������Ĺؼ���
        	 for(WebElement im:kwItems) {
             	System.out.println(im.getText());
             }
   //     }
        
        
       
        
        	
	}
}
