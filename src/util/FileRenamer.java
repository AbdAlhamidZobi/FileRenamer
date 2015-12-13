package util;

import java.io.File;
import java.text.NumberFormat;

public class FileRenamer {
    
    private FileRenamer() {}
    
    public static long reNamer(File[] files, String name, int affixLength, int startingNumber) {
        if (files == null)
            return 0;
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        numberFormat.setMaximumIntegerDigits(affixLength);
        numberFormat.setMinimumIntegerDigits(affixLength);
        numberFormat.setGroupingUsed(false);
        long maxIteration = ((long)(Math.pow(10, affixLength)) - startingNumber);
        long counter = 0;
        long succeededRenaming = 0;
        for(int i = 0; i < files.length; i++) {
            counter++;
            if (counter > maxIteration)
                return succeededRenaming;
            String[] fileNameSeparated = StringOperations.fileNameSeparator(files[i].getName());
            String extension = "";
            if (!fileNameSeparated[1].equals(""))
                extension = "." + fileNameSeparated[1];
            String affix = numberFormat.format(startingNumber++);
            File newFile = new File(files[i].getParent() + File.separator + name + affix + extension);
            if (files[i].renameTo(newFile)) {
                files[i] = newFile;
                succeededRenaming++;
            }
        }
        return succeededRenaming;
    }
}
