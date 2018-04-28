package com.baidu;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScreenShot {
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.getProperties().setProperty("webdriver.chrome.driver","E:\\Workspace\\Spider\\chromedriver.exe");
//		WebDriver webDriver = new ChromeDriver();
//		work(webDriver);
//		webDriver.close();
//	}
	public static void screenShot(WebDriver webDriver) {
		//String urlStr="http://index.baidu.com/?tpl=trend&word=%B2%C5";
		//String urlStr="http://www.sina.com.cn/";

		//webDriver.manage().timeouts().pageLoadTimeout(3600, TimeUnit.SECONDS);
		//打开url
		//webDriver.get(urlStr);
		// File srcFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);//OutputType.FILE
		 WebElement t = new WebDriverWait(webDriver, 30).until(new ExpectedCondition<WebElement>() {
				
				@Override
				public WebElement apply(WebDriver driver) {
					return webDriver.findElement(By.xpath("//table[@class='mtable profWagv']/tbody/tr[2]/td[2]"));
			
				}
			});
		 //全屏截图
		 //		 File srcFile= t.getScreenshotAs(OutputType.FILE);
//		 try {  
//	           FileUtils.copyFile(srcFile, new File("d:\\screenshot.png"));  //使用copyFile()方法保存获取到的截图文件  
//	        } catch (IOException e) {  
//	            // TODO Auto-generated catch block  
//	            e.printStackTrace();  
//	        }  
//		// webDriver.quit();  
		 
		 //定点截图
		 try {
			ImageIO.write(createElementImage(webDriver,t), "png", new File("d:\\screenshot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public static BufferedImage createElementImage(WebDriver driver,WebElement webElement)  
             throws IOException {  
             // 获得webElement的位置和大小。  
             Point location = webElement.getLocation();  
             Dimension size = webElement.getSize();  
             // 创建全屏截图。  
             BufferedImage originalImage =  
             ImageIO.read(new ByteArrayInputStream(takeScreenshot(driver)));  
             // 截取webElement所在位置的子图。  
             BufferedImage croppedImage = originalImage.getSubimage(  
             location.getX(),  
             location.getY(),  
             size.getWidth(),  
             size.getHeight());  
             return croppedImage;  
             }  
	 
	 public static byte[] takeScreenshot(WebDriver driver) throws IOException {  
	        WebDriver augmentedDriver = new Augmenter().augment(driver);  
	      return ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);  
	        }  
}
