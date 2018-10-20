package com.riskTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({"com.riskTest.controller","com.riskTest.models","com.riskTest.validate"})

/**
 * Test suite class.
 */
public class RiskTestSuite {
}