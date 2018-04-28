package com.baidu;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginBaiduIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getProperties().setProperty("webdriver.chrome.driver","E:\\Workspace\\Spider\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		work(webDriver);
		webDriver.close();
	}
	
	public static void work(WebDriver webDriver) {
		String urlStr="http://index.baidu.com/";

		webDriver.manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
		//��url
		webDriver.get(urlStr);
		//��ȡ��¼����
		WebElement loginLi=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElements(By.xpath("//div[@id='userbar']/ul/li")).get(3);
		
			}
		});
		
		WebElement loginA =loginLi.findElement(By.tagName("a"));
		loginA.click();
		//��ȡ�û����������벢��¼
		WebElement input=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				return webDriver.findElement(By.id("TANGRAM_12__userName"));
		
			}
		});	
		input.sendKeys("18852852070");
		
		
		WebElement passWord=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				return webDriver.findElement(By.id("TANGRAM_12__password"));
		
			}
		});	
		passWord.sendKeys("jjj950711");
		
		
		WebElement loginButton=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				return webDriver.findElement(By.id("TANGRAM_12__submit"));
		
			}
		});	
		loginButton.click();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����ؼ��ʲ������ѯ
		WebElement keyWord=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				return webDriver.findElement(By.id("schword"));
		
			}
		});	
		keyWord.sendKeys("�人�о�����");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement searchButton=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
			
			@Override
			public WebElement apply(WebDriver driver) {
				//System.out.println("hahahah");
				return webDriver.findElement(By.id("searchWords"));
		
			}
		});	
		searchButton.click();
		
		ScreenShot.screenShot(webDriver);
		
	}

}
