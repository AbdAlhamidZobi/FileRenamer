package util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class IOHandler { 
    
    private static final String DEFAULT_CHARSET_NAME = "UTF-8";
    private static final File HELP_FILE = new File("properties/help.dat");
    private static final File FILTERS_FILE = new File("properties/filters.properties");
    private static final File PROFILE_DIRECTORY = new File("properties");
    private static final String PROFILE_COMMENT = "File Renamer 1.0 Gábor Balázs 2014";
    
    private IOHandler() {}
    
    public static void saveProfile(String profileName, Properties profileProperties) throws IOException {
        if (!PROFILE_DIRECTORY.exists())
            PROFILE_DIRECTORY.mkdir();
        try (FileWriter writer = new FileWriter(PROFILE_DIRECTORY.getAbsolutePath() + "/" + profileName + ".profile.properties")) {
            profileProperties.store(writer, PROFILE_COMMENT);
        }
    }
    
    public static boolean deleteProfile(String profileName) {
        File profileFile = new File("properties/" + profileName + ".profile.properties");
        return profileFile.delete();
    }
    
    public static Map<String, Properties> loadAllProfiles() {
        Map<String, Properties> profiles = new TreeMap<>();
        File[] profileFiles = PROFILE_DIRECTORY.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".profile.properties");
            }
        });
        if (profileFiles == null)
            return profiles;
        if (profileFiles.length == 0)
            return profiles;
        for(File profileFile : profileFiles) {
            String fileNameWithoutExtension = StringOperations.fileNameSeparator(profileFile.getName())[0];
            fileNameWithoutExtension = fileNameWithoutExtension.substring(0, fileNameWithoutExtension.length()-8);
            Properties profileProperties = new Properties();
            try (FileReader reader = new FileReader(profileFile)) {
                profileProperties.load(reader);
            } catch (IOException ex) {
            }
            profiles.put(fileNameWithoutExtension, profileProperties);
        }
        return profiles;
    }
    
    public static void saveAllFilters(Map<String,String> filters) throws IOException {
        if (!PROFILE_DIRECTORY.exists())
            PROFILE_DIRECTORY.mkdir();
        Properties filtersProperties = new Properties();
        for(Map.Entry<String, String> filter : filters.entrySet()) {
            filtersProperties.put(filter.getKey(), filter.getValue());
        }
        try (FileWriter writer = new FileWriter(FILTERS_FILE)) {
            filtersProperties.store(writer, PROFILE_COMMENT);
        }
    }
    
    public static Map<String, String> loadAllFilters() {
        Map<String, String> filters = new TreeMap<>();
        Properties filtersProperties = new Properties();
        try (FileReader reader = new FileReader(FILTERS_FILE)) {
            filtersProperties.load(reader);
        } catch (IOException ex) {
        }
        for(String propertyName : filtersProperties.stringPropertyNames()) {
            filters.put(propertyName, filtersProperties.getProperty(propertyName));
        }
        return filters;
    }
    
    public static String loadHelpFile() {
        StringBuffer help = new StringBuffer();
        try {
            Scanner scanner = new Scanner(HELP_FILE, DEFAULT_CHARSET_NAME);
            while (scanner.hasNext())
                help = help.append(scanner.nextLine()).append("\n");
        } catch (FileNotFoundException ex) {
            return "Help file is missing. Place any help file with 'help.dat' name in 'properties' folder. If 'properties' folder is not exist then make it. Place of 'properties' folder is the same folder as the application exe.";
        } 
        return help.toString();
    }
}
