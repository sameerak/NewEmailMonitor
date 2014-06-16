package org.wso2.cep.email.monitor.ui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.automation.core.BrowserManager;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by sameerak on 6/12/14.
 */
public class emailMonitorTest {

    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
//        super.init();
        driver = BrowserManager.getWebDriver();
//        LoginPage test = new LoginPage(driver, isRunningOnCloud());
//        test.loginAs(userInfo.getUserName(), userInfo.getPassword());
//        WebAppListPage webAppListPage = new WebAppListPage(driver);
//        assertTrue("Web app context not found", webAppListPage.findWebApp("/example"));


    }

    @Test(groups = "wso2.as", description = "Verify example:servlet HelloWorldExample")
    public void testHelloWorldExample() throws Exception {

        driver.get("https://localhost:9443/emailMonitor-1.0.0/");
        Assert.assertTrue(driver.getCurrentUrl().contains("https://10.100.5.85:9443/sso/login?SAMLRequest"));

        driver.findElement(By.id("username")).sendKeys("this is a test");//xpath("//input[@id='passwd-id']")
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        assertTrue(driver.getPageSource().contains("Username or password is invalid"));

//        driver.findElement(By.xpath("//input[@type='submit']")).click();
//        assertTrue(driver.getPageSource().contains("Please configure email monitor with correct details!"));
//        driver.get(asServer.getWebAppURL() + "/example/servlets/");
//        assertEquals(driver.getTitle(), "Servlet Examples");
//        driver.findElement(By.xpath("/html/body/p[5]/table/tbody/tr/td[2]/a[2]")).click();
//        assertEquals(driver.getCurrentUrl(), asServer.getWebAppURL() +
//                "/example/servlets/servlet/HelloWorldExample");
//        assertTrue(driver.getPageSource().contains("Hello World!"));

    }
//
//    @Test(groups = "wso2.as", description = "Verify example:servlet RequestInfoExample")
//    public void testRequestInfoExample() throws Exception {
//        driver.get(asServer.getWebAppURL() + "/example/servlets/");
//        assertEquals(driver.getTitle(), "Servlet Examples");
//        driver.findElement(By.xpath("/html/body/p[5]/table/tbody/tr[2]/td[2]/a[2]")).click();
//        assertEquals(driver.getCurrentUrl(), asServer.getWebAppURL() +
//                "/example/servlets/servlet/RequestInfoExample");
//        assertTrue(driver.getPageSource().contains("Request Information Example"));
//        assertTrue(driver.getPageSource().contains("/example/servlets/servlet/RequestInfoExample"));
//    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}