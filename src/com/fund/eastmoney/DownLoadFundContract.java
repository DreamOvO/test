package com.fund.eastmoney;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DownLoadFundContract {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.getProperties().setProperty("webdriver.chrome.driver", "E:\\Workspace\\Spider\\chromedriver.exe");
		String downloadFilepath = "E:\\TXT\\pdf\\test";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("download.directory_upgrade", true);
		chromePrefs.put("download.prompt_for_download", false);
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);

		WebDriver webDriver = new ChromeDriver(cap);

		work(webDriver);
		// webDriver.close();

	}

	public static void work(WebDriver webDriver) {
		String urlStr = "http://fund.eastmoney.com/data/fundranking.html#tall;c0;r;szzf;pn50;ddesc;qsd20170424;qed20180424;qdii;zq;gg;gzbd;gzfs;bbzt;sfbb";
		// 存取每一个基金产品介绍的开始地址
		List<String> urlList = new ArrayList<String>();
		webDriver.get(urlStr);

		webDriver.manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
		// 打开url
		int count = 1;
		for (int i = 30; i < 40; i++) {
			// 获取下一页按钮
			WebElement numberPage = new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {

				@Override
				public WebElement apply(WebDriver driver) {

					return driver.findElement(By.xpath("//div[@id='pagebar']/input[@id='pnum']"));

				}
			});
			numberPage.clear();
			numberPage.sendKeys(String.valueOf(i));

			WebElement nextPage = new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {

				@Override
				public WebElement apply(WebDriver driver) {

					return driver.findElement(By.xpath("//div[@id='pagebar']/input[@class='pgo']"));

				}
			});

			nextPage.click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 锁定基金产品地址
			List<WebElement> as = new WebDriverWait(webDriver, 30).until(new ExpectedCondition<List<WebElement>>() {

				@Override
				public List<WebElement> apply(WebDriver driver) {
					return driver.findElements(By.xpath("//table[@id='dbtable']/tbody/tr/td[3]/a"));

				}
			});

			// 获取基金产品地址

			for (WebElement a : as) {
				// WebElement a=as.get(1);
				String str = "http://fund.eastmoney.com/f10/jjgg_" + findNumberFromStr(a.getAttribute("href"))
						+ "_1.html";
				urlList.add(str);
				System.out.println((count++) + "      " + str);
			}

			// nextPage.click();

		}

		/****
		 * System.out.println("基金产品地址获取完毕！！！！！！！！"); // 获取每一个产品的基金公告地址 List<String>
		 * urlList1 = new ArrayList<String>(); for (String url : urlList) {
		 * webDriver.get(url); WebElement a = new WebDriverWait(webDriver, 30).until(new
		 * ExpectedCondition<WebElement>() {
		 * 
		 * @Override public WebElement apply(WebDriver driver) { return
		 *           driver.findElement(By.xpath("//div[@class='fundDetail-footer']/ul/li[12]/a"));
		 * 
		 *           } });
		 * 
		 *           urlList1.add(a.getAttribute("href")); }
		 * 
		 *           System.out.println("基金公告地址获取完毕！！！！！！！！"); // 获取发行公告地址 List<String>
		 *           urlList2 = new ArrayList<String>(); for (String url : urlList1) {
		 *           webDriver.get(url); WebElement a = new WebDriverWait(webDriver,
		 *           30).until(new ExpectedCondition<WebElement>() {
		 * 
		 * @Override public WebElement apply(WebDriver driver) { return
		 *           driver.findElement(By.xpath("//div[@class='tabcomm
		 *           jjgg_tab']/h4/ul/li[2]/a"));
		 * 
		 *           } });
		 * 
		 *           System.out.println(a.getAttribute("href"));
		 *           urlList2.add(a.getAttribute("href")); }
		 * 
		 *           System.out.println("基金运行公告下载地址获取完毕！！！！！！！！");
		 * 
		 * 
		 ****/

		// 获取发行基金合同下载地址
		// List<String> downUrls = new ArrayList<String>();
		for (String url : urlList) { // urlList2
			webDriver.get(url);
			try {
				List<WebElement> td = new WebDriverWait(webDriver, 30).until(new ExpectedCondition<List<WebElement>>() {

					@Override
					public List<WebElement> apply(WebDriver driver) {
						return driver.findElements(By.xpath("//div[@id='ggtable']/table/tbody/tr/td[1]"));

					}
				});

				for (WebElement w : td) {
					String str = w.getText();
					WebElement a = null;
					if (str.endsWith("基金合同")) {
						a = w.findElement(By.className("pdf"));
						// a.click();
						System.out.println(a.getAttribute("href"));
						// downUrls.add(a.getAttribute("href"));
						webDriver.get(a.getAttribute("href"));
						try {
							Thread.sleep(7000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

	}

	public static String findNumberFromStr(String line) {
		System.out.println("将要匹配的字符串是：" + line);

		// 按指定模式在字符串查找
		String pattern = "(\\D*)(\\d+)(.*)";

		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		m.find();

		return m.group(2);
	}

}
