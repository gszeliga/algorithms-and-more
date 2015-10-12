package com.gzeliga.playground.algorithms.amazon;

import org.junit.Test;

import java.io.IOException;

import static com.gzeliga.playground.algorithms.amazon.OtherAmazonStuff.tokenizable;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOtherAmazonStuff {

    @Test
    public void shouldDetectTokenizableString() throws IOException {

        assertTrue(tokenizable("bedbathandbreakfast", asList("bed", "bath", "breakfast","and")));

    }

    @Test
    public void shouldDetectNonTokenizableString() throws IOException {

        assertFalse(tokenizable("sandwichasdf", asList("bed", "bath", "breakfast", "and", "sandwich")));

    }

}