import com.sun.glass.ui.SystemClipboard;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://avito.ru");
        driver.manage().window().maximize();
        Select typeSelector = new Select(driver.findElement(By.xpath("//select[@name='category_id']")));
        typeSelector.selectByValue("99");
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Принтер");
        driver.findElement(By.xpath("//div[@data-marker='search-form/region']")).click();
        driver.findElement(By.xpath("//input[@data-marker='popup-location/region/input']")).sendKeys("Владивосток");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']")).click();
        WebElement deliveryCheckbox = driver.findElement(By.xpath("//label[@data-marker='delivery-filter']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deliveryCheckbox);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
        if(!deliveryCheckbox.isSelected()) {
            deliveryCheckbox.click();
        }
        driver.findElement(By.xpath("//button[@data-marker='search-filters/submit-button']")).click();
        Select sortSelector = new Select(driver.findElement(By.xpath("//div[@class='sort-select-3QxXG select-select-box-3LBfK select-size-s-2gvAy']/select")));
        sortSelector.selectByValue("2");
        List<WebElement> results = driver.findElements(By.xpath("//div[@data-marker='item']"));
        for(int i = 0; i < 3; ++i) {
            try {
                String name = results.get(i).findElement(By.xpath(".//span[@itemprop='name']")).getText();
                String price = results.get(i).findElement(By.xpath(".//span[@itemprop='offers']")).getText();
                System.out.println(String.format("Номер объявления: %d Название: '%s' Цена: %s", i + 1, name, price));
            } catch (NoSuchElementException e) {
                System.out.println("Cannot find such element!");
            }
        }
    }
}
