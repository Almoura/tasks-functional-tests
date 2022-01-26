package br.ce.almoura.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.Assert;

public class TasksTest {
	
	public WebDriver testeAmbiente() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http:www.google.com");
		return driver;
	}
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome(); // Possibilitará a criação de instância do Chrome quando o endereço do Hub for evocado. 
		//WebDriver driver = new RemoteWebDriver(new URL("http://10.18.172.221:4445/wd/hub"), cap); // RemoteWebDriver(EndHubComQueConectar); // Ver esse end. no log do Grid
		//WebDriver driver = new RemoteWebDriver(new URL("http://172.24.0.2:4444/wd/hub"), cap); // RemoteWebDriver(EndHubComQueConectar); // Ver esse end. no log do Grid
		
		//WebDriver driver = new RemoteWebDriver(new URL("http://10.18.172.221:4444/wd/hub"), cap); // RemoteWebDriver(EndHubComQueConectar); // Ver esse end. no log do Grid
		WebDriver driver = new RemoteWebDriver(new URL("http://10.18.173.22:4444/wd/hub"), cap); // RemoteWebDriver(EndHubComQueConectar); // Ver esse end. no log do Grid
		
		
		//driver.navigate().to("http://localhost:8001/tasks");
		//driver.navigate().to("http://10.18.172.221:8001/tasks");
		
		driver.navigate().to("10.18.173.22:4444/tasks");
		 
		
		// Inibida temporariamente
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		//WebDriver driver = testeAmbiente();
		
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		 LocalDateTime now = LocalDateTime.now();
		
		try {
			// 1. Clicar no botão “Add Todo”
			driver.findElement(By.id("addTodo")).click();
				
			
			// 2. Escrever a descrição no campo "Task"
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	
			// 3. Escrever a Data
			//driver.findElement(By.id("dueDate")).sendKeys("16/01/2022");
			driver.findElement(By.id("dueDate")).sendKeys(dtf.format(now));
			
			// 4. Clicar no botão “Salvar”
			driver.findElement(By.id("saveButton")).click();
			
			// 5. Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			//Assert.assertEquals(expected, actual);
			Assert.assertEquals("Success!", message);
		} finally {
			// 6. Fechar o browser
			driver.quit();
		}
	}
	
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDateTime now = LocalDateTime.now();
		
		try {
			// 1. Clicar no botão “Add Todo”
			driver.findElement(By.id("addTodo")).click();
				
			
			// 2. Escrever a descrição no campo "Task"
			//driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	
			// 3. Escrever a Data
			//driver.findElement(By.id("dueDate")).sendKeys("26/01/2022");
			// 26/01/2022
			driver.findElement(By.id("dueDate")).sendKeys(dtf.format(now));
			
			// 4. Clicar no botão “Salvar”
			driver.findElement(By.id("saveButton")).click();
			
			// 5. Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			//Assert.assertEquals(expected, actual);
			Assert.assertEquals("Fill the task description", message);
		} finally {
			// 6. Fechar o browser
			driver.quit();
		}
	}	
	
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			// 1. Clicar no botão “Add Todo”
			driver.findElement(By.id("addTodo")).click();
				
			// 2. Escrever a descrição no campo "Task"
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	
			// 3. Escrever a Data
			//driver.findElement(By.id("dueDate")).sendKeys("03/12/2021");
			
			// 4. Clicar no botão “Salvar”
			driver.findElement(By.id("saveButton")).click();
			
			// 5. Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			//Assert.assertEquals(expected, actual);
			Assert.assertEquals("Fill the due date", message);
		} finally {
			// 6. Fechar o browser
			driver.quit();
		}
	}
		
	
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
			// 1. Clicar no botão “Add Todo”
			driver.findElement(By.id("addTodo")).click();
			
			// 2. Escrever a descrição no campo "Task"
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	
			// 3. Escrever a Data
			driver.findElement(By.id("dueDate")).sendKeys("03/11/2010");
			
			// 4. Clicar no botão “Salvar”
			driver.findElement(By.id("saveButton")).click();
			
			// 5. Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			//Assert.assertEquals(expected, actual);
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			// 6. Fechar o browser
			driver.quit();
		}
	}
	
	
	
}
