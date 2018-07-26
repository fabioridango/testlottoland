
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class userRegistration {

    WebDriver driver;

    final String users[] = {"Jan van Dam","Chack Norris","Klark n Kent","John Daw","Bat Man","Tim Los","Dave o Core",
            "Pay Pal","Lazy Cat","Jack & Johnes"};

    String expectedURL = "http://demoqa.com/registration/";

    String firstNameUser [] = new String[5];

    String lastNameUser [] = new String[5];

    String registerDistinctUsers [] = new String[5];


     /*
	  * The purpose of this method is to launch Fire fox and compare the expected URL
	  * with the address bar URL
	  *
	  * @throws Exception
	  *
	  */


    @Test (priority = 1)
    public void loadFirefoxBrowser() throws Exception
    {
        // address bar URL
        String urlAddBar;

        // set geckodriver path
        System.setProperty("webdriver.gecko.driver","/Users/fabiosiciliano/IdeaProjects/testPlayngo/src/main" +
                           "/resources/driver/geckodriver");

        // create object of firefoxDriver
        driver = new FirefoxDriver();

        // wait firefoxBrowser
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // maximize browser
        driver.manage().window().maximize();

        // launch Fire fox and direct it to the Base URL
        driver.get(expectedURL);

        // get the address bar URL
        urlAddBar = driver.getCurrentUrl();

        // compare the expected URL with the address bar URL, if are not the same the test is "failed"
        if ((expectedURL.equals(urlAddBar))){

            System.out.println("Test Passed!");

        } else {
            // close Fire fox
            driver.quit();

            // Test Failed
            throw new Exception ("Test Failed - "+""+"expected URL:"+expectedURL+" "+"is not the same of the address bar" +
                    "URL:"+ urlAddBar);
        }

        // close Fire fox
        driver.quit();
    }



     /*
	  * The purpose of this method is to store in a data structure the distinct users
	  * selected in random way
	  *
	  * @throws Exception
	  *
	  */

    @Test (priority = 2)
    public void randomDistinctUsers() throws Exception
    {

        int counterRegisterUser = 0;

        int randomNum;

        int i;

        boolean sameUser;

        while(counterRegisterUser <= (registerDistinctUsers.length - 1)) {

            randomNum = randomNumberGenerator();

            i = 0;

            sameUser = false;

            while (i <= counterRegisterUser & sameUser == false) {

                if (registerDistinctUsers[i] == users[randomNum]) {

                    sameUser = true;

                }

                i++;

            }
            if (sameUser == false) {

                registerDistinctUsers[counterRegisterUser] = users[randomNum];

                counterRegisterUser++;

            }
        }
    }


     /*
	  * The purpose of this method is to generate and return a random number
	  * between range
	  *
	  * @return int
	  *
	  * @throws Exception
	  *
	  */

    public int randomNumberGenerator() throws Exception
    {

        int numberUsers =  users.length - 1;

        Random randomUser = new Random();

        int randomNum = randomUser.nextInt(numberUsers-0) + 0;

        return randomNum;
    }


     /*
	  * The purpose of this method is to store in two data structure the first name and the last name of the distinct
	  * users selected in random way
	  *
	  * @throws Exception
	  *
	  */

    @Test (priority = 3)
    public void storeFirstLastName() throws Exception {

        char space = ' ';

        int indexString = 0;

        int counterSpace;

        int appIndexSpace = 0;

        int strlen;

        int indexRegisterUsers = 0;

        while (indexRegisterUsers <= (registerDistinctUsers.length - 1)) {

            strlen = registerDistinctUsers[indexRegisterUsers].length();

            counterSpace = 0;

            for (indexString = 0; indexString < strlen; indexString++) {

                if (registerDistinctUsers[indexRegisterUsers].charAt(indexString) == space) {

                    appIndexSpace = indexString;

                    if (counterSpace < 1) {

                        counterSpace++;

                        firstNameUser[indexRegisterUsers] = registerDistinctUsers[indexRegisterUsers].substring(0,
                                indexString);

                    } else {

                        lastNameUser[indexRegisterUsers] = registerDistinctUsers[indexRegisterUsers].substring
                                (appIndexSpace + 1, strlen);
                    }

                }
            }
            if (counterSpace == 1) {
                lastNameUser[indexRegisterUsers] = registerDistinctUsers[indexRegisterUsers].substring(appIndexSpace +
                        1, strlen);
            }

            indexRegisterUsers++;

        }

    }

     /*
	  * The purpose of this method is to register the distinct users on a website
	  * selected in random way
	  *
	  * @throws Exception
	  *
	  */

     @Test (priority = 4)
     public void registerUser() throws Exception
     {

         int numberUsers = 5;

         int indexUser = 0;

         // set geckodriver path
         System.setProperty("webdriver.gecko.driver","/Users/fabiosiciliano/IdeaProjects/testPlayngo/src/main" +
                 "/resources/driver/geckodriver");

         // create object of firefoxDriver
         driver = new FirefoxDriver();

         // wait firefoxBrowser
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

         // maximize browser
         driver.manage().window().maximize();

         // launch Fire fox and direct it to the Base URL
         driver.get(expectedURL);

          while(indexUser <= (numberUsers - 1))
          {
             // fill mandatory fields
             // first name
             driver.findElement(By.name("first_name")).sendKeys(firstNameUser[indexUser]);

             // last name
             driver.findElement(By.name("last_name")).sendKeys(lastNameUser[indexUser]);

             // checkbox hobby
             driver.findElement(By.name("checkbox_5[]")).click();

             // phone
             driver.findElement(By.name("phone_9")).sendKeys("35679067798");

             // username
             driver.findElement(By.name("username")).sendKeys("user"+indexUser);

             // email
             driver.findElement(By.name("e_mail")).sendKeys("testlottoland"+indexUser+"@gmail.it");

             // password
             driver.findElement(By.name("password")).sendKeys("12345678");

             // confirm password
             driver.findElement(By.id("confirm_password_password_2")).sendKeys("12345678");

             // submit button
             driver.findElement(By.name("pie_submit")).click();

             WebDriverWait wait = new WebDriverWait(driver, 10);

             wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("piereg_message")));

             //registration button
             driver.findElement(By.id("menu-item-374")).click();

             indexUser++;
          }

         driver.close();

     }



     /*
	  * The purpose of this method is to display the users in the list that are not
	  * already registered
	  *
	  * @throws Exception
	  *
	  */

      //@Test (priority = 5)
      public void displayNotRegisteredUser() throws Exception
      {

          int dimArrayUsers = users.length;

          int dimArrayRegisterdUsers = registerDistinctUsers.length;

              for (int i = 0; i < dimArrayUsers; i++)
              {
                  int j;

                  for (j = 0; j < dimArrayRegisterdUsers; j++)
                      if (users[i] == registerDistinctUsers[j])
                          break;

                  if (j == dimArrayRegisterdUsers)
                      System.out.print(users[i] + " ");
              }

      }

}
