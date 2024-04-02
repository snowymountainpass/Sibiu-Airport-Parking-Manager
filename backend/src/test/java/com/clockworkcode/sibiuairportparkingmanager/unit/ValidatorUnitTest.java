package com.clockworkcode.sibiuairportparkingmanager.unit;
import com.clockworkcode.sibiuairportparkingmanager.util.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorUnitTest {

    @Test
    public void testIsValidLicensePlatePattern_ValidInput() {

        String input1 = "ABC123";
        String input2 = "DTF-789-KLM";
        assertTrue(Validator.isValidPattern(input1, Validator.licensePlateValidPattern));
        assertTrue(Validator.isValidPattern(input2, Validator.licensePlateValidPattern));
    }

    @Test
    public void testIsValidLicensePlatePattern_InvalidInput() {

        String input1 = "DELETE-567-ATB";
        String input2 = "APV-567-DROP";
        assertFalse(Validator.isValidPattern(input1,  Validator.licensePlateValidPattern));
        assertFalse(Validator.isValidPattern(input2,  Validator.licensePlateValidPattern));
    }


    @Test
    public void testIsValidAirportNamePattern_ValidInput() {

        String input1 = "London Heathrow";
        String input2 = "Charles de Gaulle";
        assertTrue(Validator.isValidPattern(input1, Validator.airportNameValidPattern));
        assertTrue(Validator.isValidPattern(input2, Validator.airportNameValidPattern));
    }

    @Test
    public void testIsValidAirportNamePattern_InvalidInput() {

        String input1 = "JFK Airport ALTER";
        String input2 = "Los Angeles International TABLE";
        assertFalse(Validator.isValidPattern(input1, Validator.airportNameValidPattern));
        assertFalse(Validator.isValidPattern(input2, Validator.airportNameValidPattern));
    }


    @Test
    public void testIsValidAirportCodePattern_ValidInput() {

        String input1 = "LAX";
        String input2 = "CDG";
        assertTrue(Validator.isValidPattern(input1, Validator.airportCodeValidPattern));
        assertTrue(Validator.isValidPattern(input2, Validator.airportCodeValidPattern));
    }

    @Test
    public void testIsValidAirportCodePattern_InvalidInput() {

        String input1 = "JFK1";
        String input2 = "CD-G";
        assertFalse(Validator.isValidPattern(input1, Validator.airportCodeValidPattern));
        assertFalse(Validator.isValidPattern(input2, Validator.airportCodeValidPattern));
    }


    @Test
    public void testIsValidCostPattern_ValidInput() {

        String input1 = "101";
        String input2 = "1256";
        assertTrue(Validator.isValidPattern(input1, Validator.costValidPattern));
        assertTrue(Validator.isValidPattern(input2, Validator.costValidPattern));
    }

    @Test
    public void testIsValidCostPattern_InvalidInput() {

        String input1 = "-10";
        String input2 = "15.5";
        assertFalse(Validator.isValidPattern(input1, Validator.costValidPattern));
        assertFalse(Validator.isValidPattern(input2, Validator.costValidPattern));
    }


    @Test
    public void testIsValidParkingSpaceNamePattern_ValidInput() {

        String input1 = "0001";
        String input2 = "2345";
        assertTrue(Validator.isValidPattern(input1, Validator.parkingSpaceNamePattern));
        assertTrue(Validator.isValidPattern(input2, Validator.parkingSpaceNamePattern));
    }

    @Test
    public void testIsValidParkingSpaceNamePattern_InvalidInput() {

        String input1 = "12345";
        String input2 = "000A";
        String input3 = "0000";
        assertFalse(Validator.isValidPattern(input1, Validator.parkingSpaceNamePattern));
        assertFalse(Validator.isValidPattern(input2, Validator.parkingSpaceNamePattern));
        assertFalse(Validator.isValidPattern(input3, Validator.parkingSpaceNamePattern));
    }
}