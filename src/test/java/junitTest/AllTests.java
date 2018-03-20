package junitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ aanbiedingenJsonTest.class, AllTests.class, categorienJsonTest.class, productenJsonTest.class,
		soapTest.class })

public class AllTests {

}
