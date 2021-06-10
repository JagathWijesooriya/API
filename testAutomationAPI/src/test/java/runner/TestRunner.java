package runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.TestCases;

/**
 * test runner
 * 
 * @author Jagath
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestCases.class })
public class TestRunner {

}
