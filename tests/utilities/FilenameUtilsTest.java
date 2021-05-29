package utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilenameUtilsTest {
    private static final String FILEPATH = "D:\\test.test2.txt";
    private static final String EXPECTED_EXTENSION = "txt";

    @Test
    public void testFilenameUtils() {
        String actualExtension = new FilenameUtils().getExtension(FILEPATH);
        assertEquals(EXPECTED_EXTENSION, actualExtension);
    }
}