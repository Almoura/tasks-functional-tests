package br.ce.almoura.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class TasksTest {
	
	public WebDriver testeAmbiente() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http:www.google.com");
		return driver;
	}
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		
		// Inibida temporariamente
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		//WebDriver driver = testeAmbiente();
		
		try {
			// 1. Clicar no botão “Add Todo”
			driver.findElement(By.id("addTodo")).click();
				
			
			// 2. Escrever a descrição no campo "Task"
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	
			// 3. Escrever a Data
			driver.findElement(By.id("dueDate")).sendKeys("13/01/2022");
			
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
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		
		try {
			// 1. Clicar no botão “Add Todo”
			driver.findElement(By.id("addTodo")).click();
				
			
			// 2. Escrever a descrição no campo "Task"
			//driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	
			// 3. Escrever a Data
			driver.findElement(By.id("dueDate")).sendKeys("13/01/2022");
			
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
	public void naoDeveSalvarTarefaSemData() {
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
	public void naoDeveSalvarTarefaComDataPassada() {
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
