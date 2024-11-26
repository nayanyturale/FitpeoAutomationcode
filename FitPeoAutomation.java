package Fitpeo_Automation_Assign;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FitPeoAutomation {
    public static void main(String[] args) {
        
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
          //  Navigate to FitPeo Homepage
            driver.get("https://www.fitpeo.com");
            driver.manage().window().maximize();
               
            
           // Navigate to Revenue Calculator Page
            WebElement revenueCalculatorLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Revenue Calculator")));
            revenueCalculatorLink.click();

        // Scroll down to the slider section
       WebElement slider = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-16i48op']"))); 
           js.executeScript("arguments[0].scrollIntoView(true);", slider);

           
                     driver.findElement(By.xpath("//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']")).sendKeys("2000");


            List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

            
            for (int i = 0; i < checkboxes.size(); i++) {
                if (i == 0 || i == 1 || i == 2 || i == 3) { 
                    WebElement checkbox = checkboxes.get(i);
                    if (!checkbox.isSelected()) {
                        checkbox.click();
                        System.out.println("Clicked checkbox at index: " + i);
                    }
                }
                    
            //Validate total recurring reimbursement
           WebElement totalReimbursementHeader = driver.findElement(By.cssSelector("p:nth-child(4) p:nth-child(1)")); 
           String totalReimbursement = totalReimbursementHeader.getText();
            if (!totalReimbursement.contains("$114500")) {
                throw new AssertionError("Total Recurring Reimbursement is incorrect!");
            }

            System.out.println("Automation script executed successfully!");
            }
            }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
           driver.quit();
        }
    }
}

