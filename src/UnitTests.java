import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The main driver class for running the unit tests for all defined interfaces in assignment 3 of
 * COMP 2150, Winter 2023.
 */
public class UnitTests {
    public static void main(String[] args) {
        runTests(TimeDrivenSimulationTest.class, TimeDrivenSimulationMain.simulation().getClass());
        runTests(QueueTest.class, TimeDrivenSimulationMain.queue().getClass());
        runTests(VehicleTest.class, TimeDrivenSimulationMain.vehicle().getClass());
        runTests(RoadTest.class, TimeDrivenSimulationMain.road().getClass());
        System.out.printf("A total of %d/%d tests passed.", successfulTests, testCount);
    }

    // WARNING: HERE BE DRAGONS.
    //
    // For the purposes of this course right now, everything below can be
    // considered magic.
    //
    // The three magical things below are:
    //
    // 1. Using ANSI escape codes to change the colour of text printed to the
    // terminal.
    // 2. Declaring an annotation that we're using above on test methods (the
    // line on top of each method that is @UnitTest).
    // 3. Using Java's reflection feature to find methods declared in this class
    // that have the @UnitTest annotation, then invoking those methods on an
    // instance of this class. In short: this means we can add new test
    // methods to this class by writing a new method, adding the @UnitTest
    // annotation on top of it, and then running `main` will automatically
    // call the method that you just added.

    private static int testCount = 0;
    private static int successfulTests = 0;

    /**
     * An annotation for marking methods as tests.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public static @interface UnitTest {
    }

    /**
     * Color codes come from @see
     * <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#3-bit_and_4-bit"> List of color codes
     * on Wikipedia</a>.
     */
    private static final String RED = ((char) 27) + "[31m";
    private static final String GREEN = ((char) 27) + "[32m";
    private static final String BLUE = ((char) 27) + "[34m";
    private static final String RESET = ((char) 27) + "[39m";

    private static void runTests(final Class<?> testSuiteClass, final Class<?> instanceType) {
        final Method[] methods = testSuiteClass.getMethods();

        for (final Method method : methods) {
            if (method.getAnnotation(UnitTests.UnitTest.class) != null) {
                testCount++;
                System.out.printf("[Test %d %sINFO%s] Running test %s#%s\n", testCount, BLUE, RESET,
                        testSuiteClass.getName(), method.getName());
                try {
                    final Object testInstance = instanceType.getConstructor().newInstance();
                    Object testSuite = null;
                    for (Class<?> iface : instanceType.getInterfaces()) {
                        try {
                            testSuite =
                                    testSuiteClass.getConstructor(iface).newInstance(testInstance);
                        } catch (Exception e) {
                        }
                    }
                    if (testSuite == null) {
                        System.out.printf(
                                "%sYour implementation class must implement the interface for the test suite.%s",
                                RED, RESET);
                        System.exit(1);
                    }
                    final Object returned = method.invoke(testSuite);
                    if (returned instanceof Boolean && (Boolean) returned) {
                        successfulTests++;
                        System.out.printf("[Test %d %sPASS%s]: %s#%s\n", testCount, GREEN, RESET,
                                testSuiteClass.getName(), method.getName());
                    } else {
                        System.out.printf("[Test %d %sFAIL%s]: %s#%s\n", testCount, RED, RESET,
                                testSuiteClass.getName(), method.getName());
                    }
                } catch (final IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException e) {
                    System.err.printf("[Test %d %sFAIL%s] Failed to execute test: %s\n", testCount,
                            RED, RESET, method.getName());
                    e.printStackTrace();
                    System.err
                            .println("A test has failed, see above. Some tests may not have run.");
                    System.exit(1);
                } catch (InstantiationException | NoSuchMethodException | SecurityException e) {
                    System.err.printf("%sYour implementation *must* have a null constructor%s.",
                            RED, RESET);
                    e.printStackTrace();
                    System.exit(1);
                } catch (NullPointerException e) {
                    System.err.printf(
                            "%sYou need to replace the class reference with your own implementation.%s\n",
                            RED, RESET);
                    System.exit(1);
                } catch (final Throwable e) { // catchall for any other kind of weird exception
                                              // raised by a test.
                    System.err.printf("[Test %d %sFAIL%s] Failed to execute test: %s\n", testCount,
                            RED, RESET, method.getName());
                    e.printStackTrace();
                    System.err
                            .println("A test has failed, see above. Some tests may not have run.");
                    System.exit(1);
                }
            }
        }
    }
}
