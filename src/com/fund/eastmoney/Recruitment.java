package com.fund.eastmoney;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
/*
 * 从天天基金网页上直接爬取投资策略等信息
 * 
 */
public class Recruitment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getProperties().setProperty("webdriver.chrome.driver","E:\\Workspace\\Spider\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		work(webDriver);
		webDriver.close();
	}
	public static void work(WebDriver webDriver) {
		String urlStr="http://fund.eastmoney.com/data/fundranking.html#tall;c0;r;szzf;pn50;ddesc;qsd20170413;qed20180413;qdii;zq;gg;gzbd;gzfs;bbzt;sfbb";

		webDriver.manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
		//打开url
		webDriver.get(urlStr);
		List<WebElement> as=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<List<WebElement>>() {
			
			@Override
			public List <WebElement> apply(WebDriver driver) {
				return driver.findElements(By.xpath("//table[@id='dbtable']/tbody/tr/td[3]/a"));
		
			}
		});
		
		
		//获取基金产品地址
		int count=1;
		List <String> urlList=new ArrayList<String>();
		for(WebElement a:as) {
			urlList.add(a.getAttribute("href"));
			System.out.println((count++)+a.getAttribute("href"));
		}
		
		
		//获取每一个产品的基本概况
		List <String> urlList1=new ArrayList<String>();
		for(String url:urlList) {
			webDriver.get(url);
			WebElement a=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
				
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath("//div[@class='fundDetail-footer']/ul/li[2]/a"));
			
				}
			});
			
			urlList1.add(a.getAttribute("href"));
		}
		
		
		
		//获取投资信息
		int namecount=1;
		//List <String> urlList2=new ArrayList<String>();
		for(String url:urlList1) {
			
			System.out.println(url);
			webDriver.get(url);
			WebElement p=new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
				
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath("//div[@class='txt_cont']/div[@class='txt_in']/div[5]/div/p"));
			
				}
			});
			System.out.println(p.getText());
			//urlList1.add(a.getAttribute("href"));
			
			
		//文件写入流
			File file =new File("E:\\TXT\\"+(namecount++)+".txt");
			try {
				FileWriter outOne=new FileWriter(file);
				BufferedWriter outTwo =new BufferedWriter(outOne);
				outTwo.write(p.getText());
				outTwo.close();
				outOne.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

		
	}
}
