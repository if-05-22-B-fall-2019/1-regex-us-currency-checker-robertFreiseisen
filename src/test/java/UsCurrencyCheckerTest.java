import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

public class UsCurrencyCheckerTest {
    @Test
    public void itShouldMatchAValidCurrencyString() {
        checkRegex("$ 149.50");
    }

    private void checkRegex(final String stringToBeChecked) {
        checkRegex(stringToBeChecked, stringToBeChecked);
    }

    private void checkRegex(final String stringToBeChecked, final String expectedMatch) {
        Matcher matcher = UsCurrencyChecker.getMatcher(stringToBeChecked);
        assertTrue(matcher.find());
        assertEquals(expectedMatch, matcher.group());
        assertFalse(matcher.find());
    }

    @Test
    public void itShouldNotMatchAStringWithouCurrency() {
        Matcher matcher = UsCurrencyChecker.getMatcher("This is text without any currency information");
        assertFalse(matcher.find());
    }

    @Test
    public void itShouldMatchACurrencyWithNoBlankBetweenDollarAndFirstDigit() {
        checkRegex("$149.50");
    }

    @Test
    public void itShouldMatchACurrencyWithAMissingZeroAtEnd() {
        checkRegex("$149.5");
    }

    @Test
    public void itShouldNotMatchADifferentCharThanBlankBetweenDollarAndFirstDigit() {
        Matcher matcher = UsCurrencyChecker.getMatcher("$x14.5");
        assertFalse(matcher.find());
    }

    @Test
    public void itShouldMatchANumberWithNoDecimalPoint() {
        checkRegex("$ 200");
    }

    @Test
    public void itShouldMatchACurrencyEmbeddedInAText() {
        checkRegex("This makes $ 149.30 in total", "$ 149.30");
    }

    @Test
    public void itShouldMatchACurrencyWithMoreThanTwoDecimals() {
        checkRegex("$1.214", "$1.21");
    }

    @Test
    public void itShouldMatchTheShortestCurrencyPossible() {
        checkRegex("$3");
    }

    @Test
    public void itShouldMatchACurrencyWithADecimalPointButNoDecimals() {
        checkRegex("$ 3468.", "$ 3468");
    }

    @Test
    public void itShouldNotMatchACommaInsteadOfADecimalPoint() {
        checkRegex("$ 3,14", "$ 3");
    }
}