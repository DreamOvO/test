package com.fund.index;
/*
 * 东方财富网的指数中心
 * 
 * 
 */
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.data.dao.SaveFundStyleDao;

public class FundIndex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getProperties().setProperty("webdriver.chrome.driver", "E:\\Workspace\\Spider\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		work(webDriver);
		webDriver.close();

	}

	public static void work(WebDriver webDriver) {
		String urlStr = "http://quote.eastmoney.com/centerv2/hszs/zscf";

		webDriver.manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
		// 打开url

		webDriver.get(urlStr);
		int count=1;
		for (int i = 0; i < 23; i++) {
			List<WebElement> as = new WebDriverWait(webDriver, 300).until(new ExpectedCondition<List<WebElement>>() {

				@Override
				public List<WebElement> apply(WebDriver driver) {
					return driver.findElements(By.xpath("//table[@id='common_table']/tbody/tr/td[3]/a"));

				}
			});

			for (WebElement a : as) {
				System.out.println((count++)+"     "+a.getText());
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			WebElement a = new WebDriverWait(webDriver, 300).until(new ExpectedCondition<WebElement>() {

				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.id("common_table_next"));

				}
			});
			if (i != 22)
				a.click();
		
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
