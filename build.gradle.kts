plugins {
    id("java")
}

group = "com.hermesou"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //JUnit platform
    // to group tests by package, by class name, by class name pattern, etc (use @Suite, @SelectClasses) :junit-platform-suite-api:1.9.2
    // and to filter/discover and run them         (SuiteLauncher, SuiteTestEngine, SuiteTestDescriptor) :junit-suite-engine:1.9.2
    testImplementation("org.junit.platform:junit-platform-suite:1.9.2")
    //Launcher, engine discovery
    testImplementation("org.junit.platform:junit-platform-launcher:1.9.2")//to run tests

    //JUnit Jupiter
    //to use assertions and so on
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    //to use @ParameterizedTest
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    //Jupiter engine to run junit5 tests (JupiterTestEngine, Extensions, etc)
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks.test {
    useJUnitPlatform()
}