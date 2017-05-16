package web_test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestClient_fileUpload.class, TestClient_Security.class, TestClient_sendSQL.class, TestLogin.class,
		TestLogin_Security.class })
public class WebApp_Tests {

}
