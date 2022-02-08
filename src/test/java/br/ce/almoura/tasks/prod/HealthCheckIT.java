package br.ce.almoura.tasks.prod;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {
	@Test
	public void healthCheck() throws MalformedURLException {
		// Acessar a aplica��o de produ��o e verificar que o build se encontra l�
		DesiredCapabilities cap = DesiredCapabilities.chrome(); // Possibilitar� a cria��o de inst�ncia do Chrome quando o endere�o do Hub for evocado. 
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.22:4444/wd/hub"), cap); // RemoteWebDriver(EndHubComQueConectar); // Ver esse end. no log do Grid
		
		//WebDriver driver = new ChromeDriver();
		try {
			driver.navigate().to("192.168.0.22:9999/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
			System.out.println(version); // Imprimir�, por exemplo: build_13
		} finally {
			driver.quit();
		}
	}
}
