package utilities;

public class CursorUtils {
    public int getCurrentRow(String strSequence, int offset) {
        int row = 1;

        for (int i = 0; i < offset; i++) {
            char currentChar = strSequence.charAt(i);
            if (currentChar == '\n') {
                row++;
            }
        }
        return row;
    }

    public int getCurrentColumn(String strSequence, int offset) {
        int column = 1;

        for (int i = 0; i < offset; i++) {
            char currentChar = strSequence.charAt(i);
            if (currentChar == '\n') {
                column = 1;
            }
            else {
                column++;
            }
        }
        return column;
    }
}
