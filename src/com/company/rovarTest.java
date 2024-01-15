package com.company;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class rovarTest {

    rovar roverTester = new rovar();
    String expectedOutput;

    /** enrov tests */
    @Test
    public void enrovWithNonEmptyString1() {
        // Check characters a-ö values
        expectedOutput = "abobcocdodefofgoghohijojkoklolmomnonopopqoqrorsostotuvovwowxoxyzozåäö";
        assertEquals(expectedOutput, roverTester.enrov("abcdefghijklmnopqrstuvwxyzåäö"));
    }
    @Test
    public void enrovWithNonEmptyString2() {
        // Check characters A-Ö values
        expectedOutput = "ABOBCOCDODEFOFGOGHOHIJOJKOKLOLMOMNONOPOPQOQRORSOSTOTUVOVWOWXOXYZOZÅÄÖ";
        assertEquals(expectedOutput, roverTester.enrov("ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ"));
    }
    @Test
    public void enrovWithNonEmptyString3() {
        // Check Numbers 0-9 values
        expectedOutput = "0123456789";
        assertEquals(expectedOutput,  roverTester.enrov("0123456789"));
    }
    @Test
    public void enrovWithNonEmptyString4() {
        // Check special characters values
        expectedOutput = "!\"#€%&/(),.";
        assertEquals(expectedOutput,  roverTester.enrov("!\"#€%&/(),."));
    }

    @Test
    public void enrovStringWithEmptyString() {
        // Check empty string value
        expectedOutput = "";
        assertEquals(expectedOutput,  roverTester.enrov(""));
    }

    @Test
    public void enrovStringWithNullString() {

        // Check null value
        expectedOutput = null;
        assertEquals(expectedOutput,  roverTester.enrov(null));
    }


    /** derov tests */
    @Test
    public void derovWithNonEmptyString1() {
        // Check characters a-ö values
        expectedOutput = "abcdefghijklmnopqrstuvwxyzåäö";
        assertEquals(expectedOutput, roverTester.derov("abobcocdodefofgoghohijojkoklolmomnonopopqoqrorsostotuvovwowxoxyzozåäö"));
    }

    @Test
    public void derovWithNonEmptyString2() {
        // Check characters A-Ö values
        expectedOutput = "ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ";
        assertEquals(expectedOutput, roverTester.derov("ABOBCOCDODEFOFGOGHOHIJOJKOKLOLMOMNONOPOPQOQRORSOSTOTUVOVWOWXOXYZOZÅÄÖ"));
    }

    @Test
    public void derovWithNonEmptyStrin3() {
        // Check Numbers 0-9 values
        expectedOutput = "0123456789";
        assertEquals(expectedOutput,  roverTester.derov("0123456789"));
    }

    @Test
    public void derovWithNonEmptyStrin4() {
        // Check special characters values
        expectedOutput = "!\"#€%&/(),.";
        assertEquals(expectedOutput,  roverTester.derov("!\"#€%&/(),."));
    }

    @Test
    public void derovStringWithEmptyString() {
        // Check empty string value
        expectedOutput = "";
        assertEquals(expectedOutput,  roverTester.derov(""));
    }

    @Test
    public void derovStringWithNullString() {
        // Check null value
        expectedOutput = null;
        assertEquals(expectedOutput,  roverTester.derov(null));
    }
}