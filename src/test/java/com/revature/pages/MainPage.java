// package com.revature.pages;

// import javax.annotation.PostConstruct;

// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.support.PageFactory;
// import org.springframework.beans.factory.annotation.Autowired;

// public class MainPage {

//     @Autowired
//     public WebDriver driver;

//     @PostConstruct
//     public void initDriver(){
//         PageFactory.initElements(driver, this);
//     }

//     public void navigate(String url){
//         this.driver.navigate().to(url);
//     }
// }