package Steps;
import io.cucumber.java.Before;
import io.cucumber.java.ru.*;
import io.cucumber.java.ParameterType;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;

public class StepDefs {
    private WebDriver driver;

    @ParameterType(".*")
    public Categories categories(String category) {
        return Categories.valueOf(category);
    }

    @ParameterType(".*")
    public SortOrder sortOrder(String order) {
        return SortOrder.valueOf(order);
    }

    @Пусть("открыт ресурс авито")
    public void открытРесурсАвито() {
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://avito.ru");
    }

    @И("в выпадающем списке категорий выбрана {categories}")
    public void вВыпадающемСпискеКатегорийВыбранаОргтехника(Categories category) {
        Select typeSelector = new Select(driver.findElement(By.xpath("//select[@name='category_id']")));
        typeSelector.selectByValue(Integer.toString(category.getId()));
    }

    @И("в поле поиска введено значение {word}")
    public void вПолеПоискаВведеноЗначениеТN(String value) {
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys(value);
    }

    @Тогда("кликнуть по выпадающему списку региона")
    public void кликнутьПоВыпадающемуСпискуРегиона() {
        driver.findElement(By.xpath("//div[@data-marker='search-form/region']")).click();
    }

    @Тогда("в поле регион введено значение {word}")
    public void вПолеРегионВведеноЗначениеN(String city) throws InterruptedException {
        driver.findElement(By.xpath("//input[@data-marker='popup-location/region/input']")).sendKeys(city);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[@data-marker='suggest(0)']")).click();
    }

    @И("нажата кнопка показать объявления")
    public void нажатаКнопкаПоказатьОбъявления() {
        driver.findElement(By.xpath("//button[@data-marker='popup-location/save-button']")).click();
    }

    @Тогда("открылась страница результаты по запросу {word}")
    public void открыласьСтраницаРезультатыПоЗапросуN(String value) {

    }

    @И("активирован чекбокс только с фотографией")
    public void активированЧекбоксТолькоСФотографией() {
        WebElement checkbox = driver.findElement(By.xpath("//label[@class='checkbox-checkbox-7igZ6 checkbox-size-s-yHrZq']/input[@name='withImagesOnly']"));
        if(!checkbox.isSelected()) {
            checkbox.click();
        }
        driver.findElement(By.xpath("//button[@data-marker='search-filters/submit-button']")).click();
    }

    @И("в выпадающем списке сортировка выбрано значение {sortOrder}")
    public void вВыпадающемСпискеСортировкаВыбраноЗначениеN(SortOrder sortOrder) {
        Select sortSelector = new Select(driver.findElement(By.xpath("//div[@class='sort-select-3QxXG select-select-box-3LBfK select-size-s-2gvAy']/select")));
        sortSelector.selectByValue(Integer.toString(sortOrder.getId()));
    }

    @И("в консоль выведено значение названия и цены {int} первых товаров")
    public void вКонсольВыведеноЗначениеНазванияИЦеныПервыхТоваров(int cnt) {
        List<WebElement> results = driver.findElements(By.xpath("//div[@data-marker='item']"));
        for(int i = 0; i < cnt; ++i) {
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
