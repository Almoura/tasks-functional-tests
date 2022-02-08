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
		// Acessar a aplicação de produção e verificar que o build se encontra lá
		DesiredCapabilities cap = DesiredCapabilities.chrome(); // Possibilitará a criação de instância do Chrome quando o endereço do Hub for evocado. 
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.22:4444/wd/hub"), cap); // RemoteWebDriver(EndHubComQueConectar); // Ver esse end. no log do Grid
		
		//WebDriver driver = new ChromeDriver();
		try {
			driver.navigate().to("192.168.0.22:9999/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
			System.out.println(version); // Imprimirá, por exemplo: build_13
		} finally {
			driver.quit();
		}
	}
}
