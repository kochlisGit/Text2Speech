package utilities;

public class FilenameUtils {
    public String getExtension(String filePath) {
        String[] splittedPath = filePath.split("\\.");
        return splittedPath[splittedPath.length-1];
    }
}
