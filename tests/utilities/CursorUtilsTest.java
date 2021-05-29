package utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursorUtilsTest {
    private static final String TEXT = "abc\nABC\n  \n abcABC\n";
    private static final int OFFSET = 15;
    private static final int EXPECTED_ROW = 4;
    private static final int EXPECTED_COLUMN = 5;

    @Test
    public void testCursorUtilsRow() {
        int actualRow = new CursorUtils().getCurrentRow(TEXT, OFFSET);
        assertEquals(EXPECTED_ROW, actualRow);
    }

    @Test
    public void testCursorUtilsColumn() {
        int actualColumn = new CursorUtils().getCurrentColumn(TEXT, OFFSET);
        assertEquals(EXPECTED_COLUMN, actualColumn);
    }
}