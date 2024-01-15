package com.company;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClockTest {

    Clock clockTester = new Clock();
    String expectedOutput;

/** KOMPLITERING: Det som ska fixas är följande:
 *
 * 1. i alla dessa metoder ska den första raden tas bort, 'clockTeser.state...' ska/får inte användas.
 * istället ska metoderna börja med någon dessa: clockTester.ready() / clockTester.set(...) / clockTester.changeMode() OBS State.DISPLAY_TIME är defualt tillståndet.
 * detta innebär att man får stegra genom metoderna/tillståndsmaskinen tills man hamnar i det tillstånd som nu ges av första raden (clockTester.state = State.xxx)
 *
 * 2. den andra grejen som ska fixas är att varje testfall/metod ska enbart använda en enda assertEquals(...)
 * Detta innebär dock i praktik att man bara kan dela upp testfallen till fler metoder för alla Boundery-metoder
 * och transition behövs nog inte delas upp alls, utan man bara använder den sista assertEquals, och ersätter resten med clockTester.ready() / clockTester.set(...) / clockTester.changeMode()*/

    @Test
    void timeBoundaryZero() {
        // Check zero values
        clockTester.state = State.DISPLAY_TIME;
        clockTester.ready();
        expectedOutput = "Time set to: Hour: 0 Minute: 0 Second: 0";
        assertEquals(expectedOutput,  clockTester.set(0,0,0));
        /** PASS **/
    }

    @Test
    void timeBoundaryNegative() {
        // Check negative values
        clockTester.state = State.DISPLAY_TIME;
        clockTester.ready();
        expectedOutput = "Time set to: Error! Negative value";
        assertEquals(expectedOutput,  clockTester.set(-1,1,1));
        clockTester.ready();
        assertEquals(expectedOutput,  clockTester.set(1,-1,1));
        clockTester.ready();
        assertEquals(expectedOutput,  clockTester.set(1,1,-1));
        /** PASS **/
    }

    @Test
    void timeBoundaryToHigh() {
        // Check to high values
        clockTester.state = State.DISPLAY_TIME;
        clockTester.ready();
        expectedOutput = "Time set to: Error! Hour can not be higher then 24";
        assertEquals(expectedOutput,  clockTester.set(25,1,1));
        clockTester.ready();
        expectedOutput  = "Time set to: Error! Minute can not be higher then 59";
        assertEquals(expectedOutput,  clockTester.set(1,60,1));
        clockTester.ready();
        expectedOutput  = "Time set to: Error! Second can not be higher then 59";
        assertEquals(expectedOutput,  clockTester.set(1,1,60));
        /** PASS **/
    }

    @Test
    void timeBoundaryCloseToLimit() {
        // Check to high values
        clockTester.state = State.DISPLAY_TIME;
        clockTester.ready();
        expectedOutput = "Time set to: Hour: 23 Minute: 1 Second: 1";
        assertEquals(expectedOutput,  clockTester.set(23,1,1));
        clockTester.ready();
        expectedOutput  = "Time set to: Hour: 1 Minute: 59 Second: 1";
        assertEquals(expectedOutput,  clockTester.set(1,59,1));
        clockTester.ready();
        expectedOutput  = "Time set to: Hour: 1 Minute: 1 Second: 59";
        assertEquals(expectedOutput,  clockTester.set(1,1,59));
        /** PASS **/
    }

    @Test
    void dateBoundaryZero() {
        // Check zero values
        clockTester.state = State.DISPLAY_DATE;
        clockTester.ready();
        expectedOutput = "Date set to: Error! Negative or Zero value";
        assertEquals(expectedOutput,  clockTester.set(1,1,0));
        clockTester.ready();
        assertEquals(expectedOutput,  clockTester.set(1,0,1));
        clockTester.ready();
        assertEquals(expectedOutput,  clockTester.set(0,1,1));
        /** PASS **/
    }

    @Test
    void dateBoundaryNegative() {
        // Check zero values
        clockTester.state = State.DISPLAY_DATE;
        clockTester.ready();
        expectedOutput = "Date set to: Error! Negative or Zero value";
        assertEquals(expectedOutput,  clockTester.set(1,1,-1));
        clockTester.ready();
        assertEquals(expectedOutput,  clockTester.set(1,-1,1));
        clockTester.ready();
        assertEquals(expectedOutput,  clockTester.set(-1,1,1));
        /** PASS **/
    }

    @Test
    void dateBoundaryToHigh() {
        // Check to high values
        clockTester.state = State.DISPLAY_DATE;
        clockTester.ready();
        expectedOutput  = "Date set to: Error! Month can not be higher then 12";
        assertEquals(expectedOutput,  clockTester.set(2024,13,1));
        clockTester.ready();
        expectedOutput  = "Date set to: Error! Day can not be higher then 31";
        assertEquals(expectedOutput,  clockTester.set(2024,1,32));
        /** PASS **/
    }

    @Test
    void dateBoundaryCloseToLimit() {
        // Check to high values
        clockTester.state = State.DISPLAY_DATE;
        clockTester.ready();
        expectedOutput  = "Date set to: Year: 2024 Month: 12 Day: 1";
        assertEquals(expectedOutput,  clockTester.set(2024,12,1));
        clockTester.ready();
        expectedOutput  = "Date set to: Year: 2024 Month: 1 Day: 31";
        assertEquals(expectedOutput,  clockTester.set(2024,1,31));
        /** PASS **/
    }

    @Test
    void transition_1_3_1() {
        // Check Transition-1-3-1, From Display_Time to Change_Time to Display_Time
        clockTester.state = State.DISPLAY_TIME;
        expectedOutput = "Time ready to be set!";
        assertEquals(expectedOutput,  clockTester.ready());
        expectedOutput = "Time set to: Hour: 20 Minute: 30 Second: 15";
        assertEquals(expectedOutput,  clockTester.set(20,30,15));
        /** PASS **/
    }

    @Test
    void transisition_1_2_1() {
        // Check Transition-1-2-1, From Display_Time to Display_Date to Display_Time
        clockTester.state = State.DISPLAY_TIME;
        expectedOutput = "Year: 2024 Month: 1 Day: 1";
        assertEquals(expectedOutput,  clockTester.changeMode());
        expectedOutput = "Hour: 0 Minute: 0 Second: 0";
        assertEquals(expectedOutput,  clockTester.changeMode());
        /** PASS **/
    }

    @Test
    void transisition_1_2_4() {
        // Check Transition-1-2-4, From Display_Time to Display_Date to Change_Date
        clockTester.state = State.DISPLAY_TIME;
        expectedOutput = "Year: 2024 Month: 1 Day: 1";
        assertEquals(expectedOutput,  clockTester.changeMode());
        expectedOutput = "Date ready to be set!";
        assertEquals(expectedOutput,  clockTester.ready());
        /** PASS **/
    }

    @Test
    void transition_2_4_2() {
        // Check Transition-2-4-2, From Display_Date to Change_Date to Display_Date
        clockTester.state = State.DISPLAY_DATE;
        expectedOutput = "Date ready to be set!";
        assertEquals(expectedOutput,  clockTester.ready());
        expectedOutput = "Date set to: Year: 2024 Month: 1 Day: 6";
        assertEquals(expectedOutput,  clockTester.set(2024,1,6));
        /** PASS **/
    }

    @Test
    void transisition_2_1_2() {
        // Check Transition-2-1-2, From Display_Date to Display_Time to Display_Date
        clockTester.state = State.DISPLAY_DATE;
        expectedOutput = "Hour: 0 Minute: 0 Second: 0";
        assertEquals(expectedOutput,  clockTester.changeMode());
        expectedOutput = "Year: 2024 Month: 1 Day: 1";
        assertEquals(expectedOutput,  clockTester.changeMode());
        /** PASS **/
    }

    @Test
    void transisition_2_1_3() {
        // Check Transition-2-1-3, From Display_Date to Display_Time to Change_Time
        clockTester.state = State.DISPLAY_DATE;
        expectedOutput = "Hour: 0 Minute: 0 Second: 0";
        assertEquals(expectedOutput,  clockTester.changeMode());
        expectedOutput = "Time ready to be set!";
        assertEquals(expectedOutput,  clockTester.ready());
        /** PASS **/
    }

    @Test
    void transisition_3_1_3() {
        // Check Transition-3-1-3, From Change_Time to Display_Time to Change_Time
        clockTester.state = State.CHANGE_TIME;
        expectedOutput = "Time set to: Hour: 12 Minute: 5 Second: 30";
        assertEquals(expectedOutput,  clockTester.set(12, 5, 30));
        expectedOutput = "Time ready to be set!";
        assertEquals(expectedOutput,  clockTester.ready());
        /** PASS **/
    }

    @Test
    void transisition_3_1_2() {
        // Check Transition-3-1-3, From Change_Time to Display_Time to Change_Time
        clockTester.state = State.CHANGE_TIME;
        expectedOutput = "Time set to: Hour: 12 Minute: 5 Second: 30";
        assertEquals(expectedOutput,  clockTester.set(12, 5, 30));
        expectedOutput = "Year: 2024 Month: 1 Day: 1";
        assertEquals(expectedOutput,  clockTester.changeMode());
        /** PASS **/
    }

    @Test
    void transisition_4_2_4() {
        // Check Transition-4-2-4, From Change_Date to Display_Date to Change_Date
        clockTester.state = State.CHANGE_DATE;
        expectedOutput = "Date set to: Year: 2024 Month: 1 Day: 1";
        assertEquals(expectedOutput,  clockTester.set(2024, 1, 1));
        expectedOutput = "Date ready to be set!";
        assertEquals(expectedOutput,  clockTester.ready());
        /** PASS **/
    }

    @Test
    void transisition_4_2_1() {
        // Check Transition-4-2-1, From Change_Date to Display_Date to Display_Time
        clockTester.state = State.CHANGE_DATE;
        expectedOutput = "Date set to: Year: 2024 Month: 1 Day: 1";
        assertEquals(expectedOutput,  clockTester.set(2024, 1, 1));
        expectedOutput = "Hour: 0 Minute: 0 Second: 0";
        assertEquals(expectedOutput,  clockTester.changeMode());
        /** PASS **/
    }
    @Test
    void illegalTransition1() {
        // Check illegal Transition, Set() at Display_Date
        clockTester.state = State.DISPLAY_DATE;
        expectedOutput = "Invalid set";
        assertEquals(expectedOutput,  clockTester.set(2024, 1, 1));
        /** PASS **/
    }

    @Test
    void illegalTransition2() {
        // Check illegal Transition, Set() at Display_Time
        clockTester.state = State.DISPLAY_TIME;
        expectedOutput = "Invalid set";
        assertEquals(expectedOutput,  clockTester.set(2024, 1, 1));
        /** PASS **/
    }

    @Test
    void illegalTransition3() {
        // Check illegal Transition, ChangeMode() at Change_Date
        clockTester.state = State.CHANGE_DATE;
        expectedOutput = "Invalid changeMode";
        assertEquals(expectedOutput,  clockTester.changeMode());
        /** PASS **/
    }

    @Test
    void illegalTransition4() {
        // Check illegal Transition, ChangeMode() at Change_Time
        clockTester.state = State.CHANGE_TIME;
        expectedOutput = "Invalid changeMode";
        assertEquals(expectedOutput,  clockTester.changeMode());
        /** PASS **/
    }

    @Test
    void illegalTransition5() {
        // Check illegal Transition, Ready() at Change_Date
        clockTester.state = State.CHANGE_DATE;
        expectedOutput = "Invalid ready";
        assertEquals(expectedOutput,  clockTester.ready());
        /** PASS **/
    }

    @Test
    void illegalTransition6() {
        // Check illegal Transition, Ready() at Change_Time
        clockTester.state = State.CHANGE_TIME;
        expectedOutput = "Invalid ready";
        assertEquals(expectedOutput,  clockTester.ready());
        /** PASS **/
    }
}