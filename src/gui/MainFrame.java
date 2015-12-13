package gui;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeSet;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class MainFrame extends javax.swing.JFrame {
    
    Map<String, Properties> profiles;
    Map<String, String> filters;

    public MainFrame() {
        initComponents();
        initMyComponents();
    }

    private void initMyComponents() {
        setIconImage(Toolkit.getDefaultToolkit().createImage("icons/Modify.png"));
        
        mainTabbedPane.setIconAt(0, new ImageIcon("icons/Loading.png"));
        mainTabbedPane.setIconAt(1, new ImageIcon("icons/Warning.png"));
        mainTabbedPane.setIconAt(2, new ImageIcon("icons/Info.png"));
        settingsTabbedPane.setIconAt(0, new ImageIcon("icons/Profile.png"));
        settingsTabbedPane.setIconAt(1, new ImageIcon("icons/Search.png"));
        
        profilesDeleteTextButton.setIcon(new ImageIcon("icons/Back.png"));
        deleteProfileButton.setIcon(new ImageIcon("icons/Delete.png"));
        saveProfileButton.setIcon(new ImageIcon("icons/Save.png"));
        
        filtersDeleteTextButton.setIcon(new ImageIcon("icons/Back.png"));
        deleteFilterButton.setIcon(new ImageIcon("icons/Delete.png"));
        saveFilterButton.setIcon(new ImageIcon("icons/Save.png"));
        
        profiles = util.IOHandler.loadAllProfiles();
        filters = util.IOHandler.loadAllFilters();
        initializeProfilesComboBox();
        initializeFiltersComboBox();
        initializeFileChooseComboBox();
        
        helpTextPane.setText(util.IOHandler.loadHelpFile());
    }
    
    public void initializeProfilesComboBox() {
        if (profiles.isEmpty())
            return;
        for (Map.Entry<String, Properties> profile : profiles.entrySet())
            profilesComboBox.addItem(profile.getKey());
    }
    
    public void initializeFileChooseComboBox() {
       if (filters.isEmpty())
           return;
       fileRenamingFileChooser.resetChoosableFileFilters();
       ArrayList<FileFilter> filtersList = new ArrayList();
       for(Map.Entry<String, String> filter : filters.entrySet()) {
           final String filterName = filter.getKey();
           final String filterExtensionsToString = filter.getValue();
           final TreeSet<String> filterExtensionsSet = new TreeSet();
           Scanner extensionScanner = new Scanner(filterExtensionsToString);
           extensionScanner.useDelimiter("\\,");
           while(extensionScanner.hasNext()) {
               String teszt = extensionScanner.next().trim();
               filterExtensionsSet.add(teszt);
           }
           FileFilter fileFilter = new FileFilter() {

               @Override
               public boolean accept(File f) {
                    if (f.isDirectory())
                        return true;
                    for (String extension : filterExtensionsSet)
                        if (f.getName().toUpperCase().endsWith(extension.toUpperCase()))
                            return true;
                    return false;
               }

               @Override
               public String getDescription() {
                   return filterName + " (" + filterExtensionsToString + ")";
               }
           };
           filtersList.add(fileFilter);
       }
       Collections.sort(filtersList, new Comparator<FileFilter>() {

           @Override
           public int compare(FileFilter o1, FileFilter o2) {
               return o1.getDescription().compareTo(o2.getDescription());
           }
       });
       for(FileFilter f : filtersList) {
           fileRenamingFileChooser.addChoosableFileFilter(f);
       }
    }
    
    public void initializeFiltersComboBox() {
        if(filters.isEmpty())
            return;
        filtersComboBox.removeAllItems();
        for(Map.Entry<String, String> filter : filters.entrySet()) {
            filtersComboBox.addItem(filter.getKey());
        }
    }
    
    public boolean isInputFormatRight(String patternFileName, int affixLength, int startingNumber) {
        if (patternFileName.equals(""))
            return false;
        return !(affixLength < 1 || startingNumber < 0);
    }
    
    public void showErrorMessage() {
        JOptionPane.showMessageDialog(this, "There is some format error in the given settings.\nPattern file name has to be given!\nAffix length and Starting number have to be positive integer, except 0!\nSet properly the settings form in settings->profiles!", "Settings error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainTabbedPane = new javax.swing.JTabbedPane();
        fileRenamingPanel = new javax.swing.JPanel();
        fileRenamingFileChooser = new javax.swing.JFileChooser();
        settingsPanel = new javax.swing.JPanel();
        settingsTabbedPane = new javax.swing.JTabbedPane();
        profilesPanel = new javax.swing.JPanel();
        profilesLabel = new javax.swing.JLabel();
        newProfileLabel = new javax.swing.JLabel();
        profilesComboBox = new javax.swing.JComboBox();
        newProfileTextField = new javax.swing.JTextField();
        patternFilenameLabel = new javax.swing.JLabel();
        affixLengthLabel = new javax.swing.JLabel();
        startingNumberLabel = new javax.swing.JLabel();
        patternFilenameTextField = new javax.swing.JTextField();
        affixLengthTextField = new javax.swing.JTextField();
        startingNumberTextField = new javax.swing.JTextField();
        deleteProfileButton = new javax.swing.JButton();
        saveProfileButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        profilesDeleteTextButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        filtersPanel = new javax.swing.JPanel();
        filtersComboBox = new javax.swing.JComboBox();
        filtersLabel = new javax.swing.JLabel();
        newFilterLabel = new javax.swing.JLabel();
        extensionsLabel = new javax.swing.JLabel();
        newFilterTextField = new javax.swing.JTextField();
        extensionTextField = new javax.swing.JTextField();
        filtersDeleteTextButton = new javax.swing.JButton();
        deleteFilterButton = new javax.swing.JButton();
        saveFilterButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        helpPanel = new javax.swing.JPanel();
        helpScrollPane = new javax.swing.JScrollPane();
        helpTextPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("File Renamer");
        setBounds(new java.awt.Rectangle(100, 100, 640, 480));

        mainTabbedPane.setPreferredSize(new java.awt.Dimension(800, 500));

        fileRenamingFileChooser.setDialogType(javax.swing.JFileChooser.CUSTOM_DIALOG);
        fileRenamingFileChooser.setApproveButtonText("Rename");

        fileRenamingFileChooser.setMultiSelectionEnabled(true);
        fileRenamingFileChooser.setName(""); // NOI18N
        fileRenamingFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileRenamingFileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fileRenamingPanelLayout = new javax.swing.GroupLayout(fileRenamingPanel);
        fileRenamingPanel.setLayout(fileRenamingPanelLayout);
        fileRenamingPanelLayout.setHorizontalGroup(
            fileRenamingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileRenamingPanelLayout.createSequentialGroup()
                .addComponent(fileRenamingFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        fileRenamingPanelLayout.setVerticalGroup(
            fileRenamingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileRenamingPanelLayout.createSequentialGroup()
                .addComponent(fileRenamingFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("File renaming", fileRenamingPanel);

        profilesLabel.setText("Profiles:");

        newProfileLabel.setText("New profile:");

        profilesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profilesComboBoxActionPerformed(evt);
            }
        });

        patternFilenameLabel.setText("Pattern filename:");

        affixLengthLabel.setText("Affix length:");

        startingNumberLabel.setText("Starting number:");

        deleteProfileButton.setText("Delete profile");
        deleteProfileButton.setMaximumSize(new java.awt.Dimension(110, 23));
        deleteProfileButton.setMinimumSize(new java.awt.Dimension(110, 23));
        deleteProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProfileButtonActionPerformed(evt);
            }
        });

        saveProfileButton.setText("Save profile");
        saveProfileButton.setMaximumSize(new java.awt.Dimension(110, 23));
        saveProfileButton.setMinimumSize(new java.awt.Dimension(110, 23));
        saveProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveProfileButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Saving is not needed for file renaming, but either a selected profile or a completed form is necessary.");

        profilesDeleteTextButton.setText("Delete text");
        profilesDeleteTextButton.setMaximumSize(new java.awt.Dimension(110, 23));
        profilesDeleteTextButton.setMinimumSize(new java.awt.Dimension(110, 23));
        profilesDeleteTextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profilesDeleteTextButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Description: Pattern filename + Starting number filled with nils in Affix length length.");

        jLabel3.setText("Example: PartyPhotos20141231_0001, PartyPhotos20141231_0002,...");

        javax.swing.GroupLayout profilesPanelLayout = new javax.swing.GroupLayout(profilesPanel);
        profilesPanel.setLayout(profilesPanelLayout);
        profilesPanelLayout.setHorizontalGroup(
            profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startingNumberLabel)
                    .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(affixLengthLabel)
                        .addComponent(patternFilenameLabel)
                        .addComponent(newProfileLabel))
                    .addComponent(profilesLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newProfileTextField)
                    .addComponent(profilesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(patternFilenameTextField)
                    .addComponent(affixLengthTextField)
                    .addComponent(startingNumberTextField)
                    .addGroup(profilesPanelLayout.createSequentialGroup()
                        .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addGroup(profilesPanelLayout.createSequentialGroup()
                                .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(profilesDeleteTextButton, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(deleteProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(41, 41, 41)
                                .addComponent(saveProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        profilesPanelLayout.setVerticalGroup(
            profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profilesLabel)
                    .addComponent(profilesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newProfileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newProfileLabel))
                .addGap(18, 18, 18)
                .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patternFilenameLabel)
                    .addComponent(patternFilenameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(affixLengthLabel)
                    .addComponent(affixLengthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startingNumberLabel)
                    .addComponent(startingNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(profilesDeleteTextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(profilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        settingsTabbedPane.addTab("Profiles", profilesPanel);

        filtersComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtersComboBoxActionPerformed(evt);
            }
        });

        filtersLabel.setText("Filters:");

        newFilterLabel.setText("New filter:");

        extensionsLabel.setText("Extensions:");

        filtersDeleteTextButton.setText("Delete text");
        filtersDeleteTextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtersDeleteTextButtonActionPerformed(evt);
            }
        });

        deleteFilterButton.setText("Delete filter");
        deleteFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFilterButtonActionPerformed(evt);
            }
        });

        saveFilterButton.setText("Save filter");
        saveFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFilterButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("If you would like to use a filter, save it. Then your filter will appear in the 'Files of type' combo box at once.");

        jLabel5.setText("Description: a name for a new filter + its extensions separated with comma (.ext1, .ext2, .ext3,...).");

        jLabel6.setText("Pattern: New filter: VideoFiles; Extensions: .vmw, .avi, .mp4,...");

        javax.swing.GroupLayout filtersPanelLayout = new javax.swing.GroupLayout(filtersPanel);
        filtersPanel.setLayout(filtersPanelLayout);
        filtersPanelLayout.setHorizontalGroup(
            filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filtersLabel)
                    .addComponent(newFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(extensionsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filtersComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newFilterTextField)
                    .addComponent(extensionTextField)
                    .addGroup(filtersPanelLayout.createSequentialGroup()
                        .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addGroup(filtersPanelLayout.createSequentialGroup()
                                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(filtersDeleteTextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(deleteFilterButton, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(saveFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        filtersPanelLayout.setVerticalGroup(
            filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtersComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtersLabel))
                .addGap(18, 18, 18)
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newFilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(extensionsLabel)
                    .addComponent(extensionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(filtersDeleteTextButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveFilterButton)
                    .addComponent(deleteFilterButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(274, Short.MAX_VALUE))
        );

        settingsTabbedPane.addTab("Filters", filtersPanel);

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(settingsTabbedPane)
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(settingsTabbedPane)
        );

        mainTabbedPane.addTab("Settings", settingsPanel);

        helpTextPane.setEditable(false);
        helpTextPane.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        helpScrollPane.setViewportView(helpTextPane);

        javax.swing.GroupLayout helpPanelLayout = new javax.swing.GroupLayout(helpPanel);
        helpPanel.setLayout(helpPanelLayout);
        helpPanelLayout.setHorizontalGroup(
            helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(helpScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
        );
        helpPanelLayout.setVerticalGroup(
            helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(helpScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Help & About", helpPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainTabbedPane.getAccessibleContext().setAccessibleName("tab1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileRenamingFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileRenamingFileChooserActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if (evt.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
            int affix = 0;
            int startingNumber = -1;
            try {
                affix = Integer.parseInt(affixLengthTextField.getText());
                startingNumber = Integer.parseInt(startingNumberTextField.getText());
            } catch (NumberFormatException ex) {
                setCursor(Cursor.getDefaultCursor());
                showErrorMessage();
                return;
            }
            if (!isInputFormatRight(patternFilenameTextField.getText(), affix, startingNumber)) {
                setCursor(Cursor.getDefaultCursor());
                showErrorMessage();
                return;
            }
            util.FileRenamer.reNamer(fileRenamingFileChooser.getSelectedFiles(), patternFilenameTextField.getText(), affix, startingNumber);
            fileRenamingFileChooser.rescanCurrentDirectory();
            fileRenamingFileChooser.updateUI();
            fileRenamingFileChooser.setFileFilter(fileRenamingFileChooser.getAcceptAllFileFilter());
        }
        if (evt.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
            fileRenamingFileChooser.updateUI();
            fileRenamingFileChooser.setFileFilter(fileRenamingFileChooser.getAcceptAllFileFilter());
        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_fileRenamingFileChooserActionPerformed

    private void profilesDeleteTextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profilesDeleteTextButtonActionPerformed
        newProfileTextField.setText("");
        patternFilenameTextField.setText("");
        affixLengthTextField.setText("");
        startingNumberTextField.setText("");
        profilesComboBox.setSelectedItem(null);
    }//GEN-LAST:event_profilesDeleteTextButtonActionPerformed

    private void saveProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveProfileButtonActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        String profileName = newProfileTextField.getText();
        if (profileName.equals("")) {
            setCursor(Cursor.getDefaultCursor());
            JOptionPane.showMessageDialog(this, "New profile name has to be given!", "Settings error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int affix = 0;
        int startingNumber = -1;
        try {
            affix = Integer.parseInt(affixLengthTextField.getText());
            startingNumber = Integer.parseInt(startingNumberTextField.getText());
        } catch (NumberFormatException ex) {
            setCursor(Cursor.getDefaultCursor());
            showErrorMessage();
            return;
        }
        if (!isInputFormatRight(patternFilenameTextField.getText(), affix, startingNumber)) {
            setCursor(Cursor.getDefaultCursor());
            showErrorMessage();
            return;
        }
        Properties profileSetting = new Properties();
        profileSetting.put("profile_name", profileName);
        profileSetting.put("pattern_filename", patternFilenameTextField.getText());
        profileSetting.put("affix_length", Integer.toString(affix));
        profileSetting.put("starting_number", Integer.toString(startingNumber));
        try {
            util.IOHandler.saveProfile(profileName, profileSetting);
            profiles.put(profileName, profileSetting);
            profilesComboBox.removeAllItems();
            initializeProfilesComboBox();
            profilesComboBox.setSelectedItem(profileName);
        } catch (IOException ex) {
            setCursor(Cursor.getDefaultCursor());
            JOptionPane.showMessageDialog(this, "Saving has been failured!", "IO Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_saveProfileButtonActionPerformed

    private void profilesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profilesComboBoxActionPerformed
        if (profilesComboBox.getSelectedItem() == null)
            return;
        String profileName = profilesComboBox.getSelectedItem().toString();
        if (profileName.equals(""))
            return;
        Properties profileSettings = profiles.get(profileName);
        newProfileTextField.setText(profileName);
        patternFilenameTextField.setText(profileSettings.getProperty("pattern_filename"));
        affixLengthTextField.setText(profileSettings.getProperty("affix_length"));
        startingNumberTextField.setText(profileSettings.getProperty("starting_number"));
    }//GEN-LAST:event_profilesComboBoxActionPerformed

    private void deleteProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProfileButtonActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if (profilesComboBox.getSelectedItem() == null) {
            setCursor(Cursor.getDefaultCursor());
            return;
        }
        String profileName = profilesComboBox.getSelectedItem().toString();
        if (profileName.equals("")) {
            setCursor(Cursor.getDefaultCursor());
            return;
        }
        boolean isProfileDeleteInTheList = util.IOHandler.deleteProfile(profileName);
        if (!isProfileDeleteInTheList) {
            isProfileDeleteInTheList = JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Profile file deleting has been failured.\nDo you want to delete profile in the list?", "Deleting error", JOptionPane.YES_NO_OPTION);
        }
        if (isProfileDeleteInTheList) {
            profiles.remove(profileName);
            profilesComboBox.removeItem(profileName);
        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_deleteProfileButtonActionPerformed

    private void filtersDeleteTextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtersDeleteTextButtonActionPerformed
        newFilterTextField.setText("");
        extensionTextField.setText("");
        filtersComboBox.setSelectedItem(null);
    }//GEN-LAST:event_filtersDeleteTextButtonActionPerformed

    private void saveFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveFilterButtonActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        String filterName = newFilterTextField.getText();
        String extensions = extensionTextField.getText();
        if(filterName.equals("") || extensions.equals("")) {
            setCursor(Cursor.getDefaultCursor());
            JOptionPane.showMessageDialog(this, "New filter name and at least one extension have to be given!", "Settings error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        filters.put(filterName, extensions);
        initializeFileChooseComboBox();
        initializeFiltersComboBox();
        filtersComboBox.setSelectedItem(filterName);
        try {
            util.IOHandler.saveAllFilters(filters);
        } catch (IOException ex) {
            setCursor(Cursor.getDefaultCursor());
            JOptionPane.showMessageDialog(this, "Saving has been failured!", "IO Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        setCursor(Cursor.getDefaultCursor());
        
    }//GEN-LAST:event_saveFilterButtonActionPerformed

    private void filtersComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtersComboBoxActionPerformed
        if (filtersComboBox.getSelectedItem() == null)
            return;
        String filterName = filtersComboBox.getSelectedItem().toString();
        if (filterName.equals(""))
            return;
        newFilterTextField.setText(filterName);
        extensionTextField.setText(filters.get(filterName));
    }//GEN-LAST:event_filtersComboBoxActionPerformed

    private void deleteFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFilterButtonActionPerformed
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        if (filtersComboBox.getSelectedItem() == null) {
            setCursor(Cursor.getDefaultCursor());
            return;
        }
        String filterName = filtersComboBox.getSelectedItem().toString();
        if (filterName.equals("")) {
            setCursor(Cursor.getDefaultCursor());
            return;
        }
        filters.remove(filterName);
        filtersComboBox.removeItem(filterName);
        initializeFileChooseComboBox();
        try {
            util.IOHandler.saveAllFilters(filters);
        } catch (IOException ex) {
            setCursor(Cursor.getDefaultCursor());
            JOptionPane.showMessageDialog(this, "Deleting has been failured!", "IO Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_deleteFilterButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel affixLengthLabel;
    private javax.swing.JTextField affixLengthTextField;
    private javax.swing.JButton deleteFilterButton;
    private javax.swing.JButton deleteProfileButton;
    private javax.swing.JTextField extensionTextField;
    private javax.swing.JLabel extensionsLabel;
    private javax.swing.JFileChooser fileRenamingFileChooser;
    private javax.swing.JPanel fileRenamingPanel;
    private javax.swing.JComboBox filtersComboBox;
    private javax.swing.JButton filtersDeleteTextButton;
    private javax.swing.JLabel filtersLabel;
    private javax.swing.JPanel filtersPanel;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JScrollPane helpScrollPane;
    private javax.swing.JTextPane helpTextPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JLabel newFilterLabel;
    private javax.swing.JTextField newFilterTextField;
    private javax.swing.JLabel newProfileLabel;
    private javax.swing.JTextField newProfileTextField;
    private javax.swing.JLabel patternFilenameLabel;
    private javax.swing.JTextField patternFilenameTextField;
    private javax.swing.JComboBox profilesComboBox;
    private javax.swing.JButton profilesDeleteTextButton;
    private javax.swing.JLabel profilesLabel;
    private javax.swing.JPanel profilesPanel;
    private javax.swing.JButton saveFilterButton;
    private javax.swing.JButton saveProfileButton;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JTabbedPane settingsTabbedPane;
    private javax.swing.JLabel startingNumberLabel;
    private javax.swing.JTextField startingNumberTextField;
    // End of variables declaration//GEN-END:variables
}
