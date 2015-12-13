package util;

import java.util.Scanner;

public class StringOperations {
    
    private StringOperations() {}
    
    /*
    This method is separate the file name and its extension.
    Returns in a two-element array: first element is the filename and the second one is the extension.
     */
    public static String[] fileNameSeparator (String fileName) {
        String[] fileNameSeparated = new String[2];
        fileNameSeparated[0] = "";
        fileNameSeparated[1] = "";
        
        if (fileName == null || fileName.equals(""))
            return fileNameSeparated;
        
        String fileNameWithoutExtension = "";
        String fileExtension = "";

        Scanner fileNameScanner = new Scanner(fileName);
        fileNameScanner.useDelimiter("\\.");
        int counter = 0;
        while (fileNameScanner.hasNext()) {
            counter++;
            if (counter != 0)
                fileNameWithoutExtension += fileExtension + ".";
            fileExtension = fileNameScanner.next();
        }

        if (counter == 1) {
            fileNameWithoutExtension = fileExtension;
            fileExtension = "";
        } else
            fileNameWithoutExtension = fileNameWithoutExtension.substring(1, fileNameWithoutExtension.length()-1);
        
        fileNameSeparated[0] = fileNameWithoutExtension;
        fileNameSeparated[1] = fileExtension;
        return fileNameSeparated;
    }        
}
