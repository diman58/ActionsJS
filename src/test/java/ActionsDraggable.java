import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ActionsDraggable {
    private WebDriver driver;

    @BeforeMethod
    public void broserSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        driver =null;
    }


    @Test(description = "Draggable")
    public void dragElement() {

        driver.get("https://jqueryui.com/draggable/");

        WebElement iFrame = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/iframe")));
        driver.switchTo().frame(iFrame);

        WebElement draggable = driver.findElement(By.xpath("//*[@id='draggable']"));

        new Actions(driver).dragAndDropBy(draggable
                ,draggable.getLocation().getX()+50
                ,draggable.getLocation().getY()+150).build().perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Draggable2")
    public void dragElement2 ()  {
        driver.get("https://jqueryui.com/draggable/");

        WebElement iFrame = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/iframe")));
        driver.findElement(By.xpath("//a[text()='jQuery UI Draggable + Sortable']")).click();
        driver.switchTo().frame(iFrame);


        WebElement draggable = driver.findElement(By.xpath("//*[@id='draggable']"));

        new Actions(driver).clickAndHold(draggable)
                .moveByOffset(draggable.getLocation().getX()-15
                        ,draggable.getLocation().getY()+95).build().perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Droppable2")
    public void dropElement () {
        driver.get("https://jqueryui.com/droppable/");

        WebElement iFrame = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/iframe")));
        driver.switchTo().frame(iFrame);

        WebElement draggable = driver.findElement(By.xpath("//*[@id='draggable']"));
        WebElement draggableTarget = driver.findElement(By.xpath("//*[@id='droppable']"));

        new Actions(driver).dragAndDrop(draggable,draggableTarget).build().perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "ClickAndHold")
    public void dropElement2()  {
        driver.get("https://jqueryui.com/resizable/");

        WebElement iFrame = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/iframe")));
        driver.switchTo().frame(iFrame);

        WebElement draggable = driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));

        new Actions(driver).clickAndHold(draggable).moveByOffset(100,
                100).release().build().perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "ClickAndHold")
    public void chooseElements () {
        driver.get("https://jqueryui.com/selectable/");

        WebElement iFrame = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/iframe")));
        driver.switchTo().frame(iFrame);

        WebElement item1 = driver.findElement(By.xpath("//*[@id='selectable']/li[1]"));
        WebElement item2 = driver.findElement(By.xpath("//*[@id='selectable']/li[2]"));
        WebElement item3 = driver.findElement(By.xpath("//*[@id='selectable']/li[3]"));
        WebElement item6 = driver.findElement(By.xpath("//*[@id='selectable']/li[6]"));

        new Actions(driver).keyDown(Keys.LEFT_CONTROL)
                .click(item1)
                .click(item2)
                .click(item3)
                .click(item6)
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Sortable")
    public void chooseElements2 () throws InterruptedException, AWTException {
        driver.get("https://jqueryui.com/sortable/");

        WebElement iFrame = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='content']/iframe")));
        driver.switchTo().frame(iFrame);


        WebElement item1 = driver.findElement(By.xpath("//*[@id='sortable']/li[1]"));
        WebElement item3 = driver.findElement(By.xpath("//*[@id='sortable']/li[3]"));

        new Actions(driver).clickAndHold(item1).moveByOffset(50,100).release().build().perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "JSHighlighting")
    public void JSHighlit() {
        driver.get("https://www.saucedemo.com/");

        WebElement logInBtn = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login-button']")));

        String bg = logInBtn.getCssValue("backgroundColor");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", logInBtn);

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path path = Paths.get("src/main/resources");
            //C:\Users\Dmitrii_Tsarev\IdeaProjects\asd
            FileUtils.copyFileToDirectory(screenshot, new File(path.toAbsolutePath().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", logInBtn);

        javascriptExecutor.executeScript("arguments[0].click();", logInBtn);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}