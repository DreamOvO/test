package com.fund.index;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.data.dao.SaveFundStyleDao;
import com.data.projo.FundStyle;

/*
 * 中正指数有限公司的指数集
 */
public class Chinaindex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.getProperties().setProperty("webdriver.chrome.driver", "E:\\Workspace\\Spider\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		work(webDriver);
		webDriver.close();
	}

	public static void work(WebDriver webDriver) {
		String urlStr = "http://www.csindex.com.cn/zh-CN/indices/index?";

		FundStyle fundStyle=new FundStyle();
		SaveFundStyleDao dao=new SaveFundStyleDao();
		
		webDriver.manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
		// 打开url

		webDriver.get(urlStr);
		for (int i = 0; i < 50; i++) {
			WebElement div = new WebDriverWait(webDriver, 300).until(new ExpectedCondition<WebElement>() {

				@Override
				public WebElement apply(WebDriver driver) { // i_footer
					return driver.findElement(By.xpath("//div[@class='i_footer']"));

					// return driver.findElement(By.xpath("//table[@id='table']/tbody/tr[last()]"));

				}
			});
			Actions action = new Actions(webDriver);
			action.moveToElement(div).perform();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// webDriver.manage().timeouts().implicitlyWait(360, TimeUnit.SECONDS);
		}
		
		
		List<WebElement> trs = new WebDriverWait(webDriver, 300).until(new ExpectedCondition<List<WebElement>>() {

			@Override
			public List<WebElement> apply(WebDriver driver) { // i_footer
				return driver.findElements(By.xpath("//table[@id='table']/tbody/tr"));

			}
		});
		
		
		//"//table[@id='table']/tbody/tr["+count+"]/td[1]"
		int count=1;
		for(WebElement tr:trs) {
			System.out.println(tr.getText());
			fundStyle=new FundStyle();
			fundStyle.setId(count++);
			
//			fundStyle.setIndexId(tr.findElement(By.tagName("/td[1]")).getText());
//			fundStyle.setIndexCategory(tr.findElement(By.tagName("/td[11]")).getText());
//			fundStyle.setIndexName(tr.findElement(By.tagName("/td[2]")).getText());
//			fundStyle.setProductCategory(tr.findElement(By.tagName("/td[6]")).getText());
			//System.out.println("//table[@id='table']/tbody/tr["+count+"]/td[1]");
			fundStyle.setIndexId(tr.findElement(By.xpath("//table[@id='table']/tbody/tr["+(count-1)+"]/td[1]")).getText());
			fundStyle.setIndexCategory(tr.findElement(By.xpath("//table[@id='table']/tbody/tr["+(count-1)+"]/td[11]")).getText());
			fundStyle.setIndexName(tr.findElement(By.xpath("//table[@id='table']/tbody/tr["+(count-1)+"]/td[2]")).getText());
			fundStyle.setProductCategory(tr.findElement(By.xpath("//table[@id='table']/tbody/tr["+(count-1)+"]/td[6]")).getText());
			System.out.println(fundStyle.getId()+"   "+fundStyle.getIndexCategory()+"    "+fundStyle.getIndexName()+"    "+fundStyle.getProductCategory());
			dao.save(fundStyle);
		}
		
		dao.commit();
		dao.close();
	}

}
