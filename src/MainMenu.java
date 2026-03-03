import com.sun.tools.javac.Main;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;

public class MainMenu implements ActionListener {

    JFrame menuFrame;
    JPanel welcomePanel;
    JPanel buttonPanel;
    JLabel welcomeLabel;
    JButton loadExisting;
    JButton newEntry;
    JButton exitBtn;
    JButton settingsBtn;
    JFrame settingsFrame;
    JPanel settingsPanel;
    int fontSize;

    Color bgColor;
    Color fgColor;
    Color panelColor;

    public MainMenu(){

        Config config = new Config();

        fontSize = config.getFontSize();

        if(fontSize<=1){
            fontSize = 1;
            config.setFontSize(1);
        }

        String theme = config.getTheme();

        switch(theme) {
            case "Light":
                bgColor = new Color(203, 203, 232);
                fgColor = new Color(41, 40, 40);
                panelColor = new Color(212, 212, 225);
                break;
            case "Pastel":
                bgColor = new Color(243, 239, 195);
                fgColor = new Color(60, 64, 71);
                panelColor = new Color(201, 201, 248);
                break;
            case "Pink":
                bgColor = new Color(246, 128, 237);
                fgColor = new Color(86, 27, 87);
                panelColor = new Color(237, 160, 217);
                break;
            case "Sim":
                bgColor = new Color(20, 58, 67);
                fgColor = new Color(13, 201, 225);
                panelColor = new Color(30, 31, 32);
                break;
            case "Matrix":
                bgColor = new Color(1, 23, 1);
                fgColor = new Color(29, 159, 1);
                panelColor = new Color(9, 16, 9);
                break;
            case "Midnight":
                bgColor = new Color(20, 20, 21);
                fgColor = new Color(177, 177, 184);
                panelColor = new Color(0, 0, 0);
                break;
            case "Monotone":
                bgColor = new Color(37, 37, 37);
                fgColor = new Color(223, 223, 223);
                panelColor = new Color(27, 27, 27);
                break;
            case "RedAndGreen":
                bgColor = new Color(172, 49, 49);
                fgColor = new Color(45, 179, 87);
                panelColor = new Color(232, 31, 31);
                break;
            default:
                bgColor = new Color(61, 61, 80);
                fgColor = new Color(241, 237, 237);
                panelColor = new Color(42, 42, 57);
                break;
        }

        menuFrame = new JFrame();
        menuFrame.setLayout(new BorderLayout());
        menuFrame.setSize(1920,1080);
        menuFrame.setTitle("Journal");
        ImageIcon logo = new ImageIcon("imgs/Journal.png");
        menuFrame.setIconImage(logo.getImage());

        welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridLayout(1, 1));
        welcomePanel.setBackground(panelColor);
        welcomePanel.setBounds(0, 0, 1920, 980);

        welcomeLabel = new JLabel("Journal_2K");
        welcomeLabel.setForeground(fgColor);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 89));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomePanel.add(welcomeLabel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(panelColor);
        buttonPanel.setBounds(0, 980, 1920, 100);

        Dimension btnDimension = new Dimension(120, 50);

        loadExisting = new JButton("Load Existing");
        loadExisting.setPreferredSize(btnDimension);
        loadExisting.setContentAreaFilled(false);
        loadExisting.setOpaque(true);
        loadExisting.setBackground(bgColor);
        loadExisting.setForeground(fgColor);

        newEntry = new JButton("New Entry");
        newEntry.setPreferredSize(btnDimension);
        newEntry.setContentAreaFilled(false);
        newEntry.setOpaque(true);
        newEntry.setBackground(bgColor);
        newEntry.setForeground(fgColor);

        exitBtn = new JButton("Exit");
        exitBtn.setPreferredSize(btnDimension);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setOpaque(true);
        exitBtn.setBackground(bgColor);
        exitBtn.setForeground(fgColor);

        settingsBtn = new JButton("Settings");
        settingsBtn.setPreferredSize(btnDimension);
        settingsBtn.setContentAreaFilled(false);
        settingsBtn.setOpaque(true);
        settingsBtn.setBackground(bgColor);
        settingsBtn.setForeground(fgColor);

        loadExisting.addActionListener(this);
        newEntry.addActionListener(this);
        exitBtn.addActionListener(this);
        settingsBtn.addActionListener(this);

        buttonPanel.add(loadExisting);
        buttonPanel.add(newEntry);
        buttonPanel.add(exitBtn);
        buttonPanel.add(settingsBtn);

        menuFrame.add(welcomePanel, BorderLayout.CENTER);
        menuFrame.add(buttonPanel, BorderLayout.SOUTH);



        menuFrame.setVisible(true);

        menuFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    System.exit(0);
            }
        });

    }

    JButton confirm;
    JDateChooser dateChooser;
    JFrame newEntryFrame;
    JPanel newEntryPanel;
    ImageIcon logo = new ImageIcon("imgs/Journal.png");
    JComboBox<String> themeComboBox;
    JButton saveBtn;
    JTextField fontSizeInput;

    private void openSettings(){
        //Prevent duplicate Settings windows
        if(settingsFrame!=null){
            settingsFrame.dispose();
        }

        settingsFrame = new JFrame("Settings");
        settingsFrame.setLayout(new BorderLayout());
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setSize(450,400);
        settingsFrame.setIconImage(logo.getImage());
        settingsFrame.setResizable(false);
        settingsFrame.setLocationRelativeTo(null);

        settingsPanel = new JPanel();
        settingsPanel.setBackground(panelColor);

        settingsPanel.setLayout(new GridLayout(0, 2));

        JLabel fontSizeL  = new JLabel("Font Size:");
        fontSizeL.setHorizontalAlignment(JLabel.CENTER);
        fontSizeL.setForeground(fgColor);

        fontSizeInput = new JTextField();
        fontSizeInput.setMargin(new Insets(55, 40, 55, 40));
        fontSizeInput.setBorder(BorderFactory.createLineBorder(panelColor,50));
        fontSizeInput.setBackground(bgColor);
        fontSizeInput.setForeground(fgColor);
        Config config = new Config();
        int configSize = config.getFontSize();
        fontSizeInput.setText(Integer.toString(configSize));

        JLabel themeLabel = new JLabel("Theme:");
        themeLabel.setHorizontalAlignment(JLabel.CENTER);
        themeLabel.setForeground(fgColor);

        String[] themes = {"Dark", "Light", "Pastel", "Pink", "Sim", "Matrix", "Midnight", "Monotone", "RedAndGreen" };

        themeComboBox = new JComboBox<String>(themes);

        themeComboBox.setSelectedItem(config.getTheme());
        themeComboBox.setBorder(BorderFactory.createLineBorder(panelColor, 60));


        themeComboBox.setUI(new BasicComboBoxUI() {

            //Align dropdown with the JComboBox
            @Override
            protected ComboPopup createPopup() {
                @SuppressWarnings("rawtypes")
                BasicComboPopup popup = new BasicComboPopup((JComboBox) themeComboBox) {
                    //Offset popup to align with JComboBox with border added
                    @Override
                    protected Rectangle computePopupBounds(int px, int py, int pw, int ph) {

                        int borderOffset = Integer.parseInt(String.valueOf(themeComboBox.getBorder().getBorderInsets(themeComboBox).top));

                        return super.computePopupBounds(px + borderOffset, py - borderOffset, pw - (2*borderOffset), ph);
                    }
                };
                return popup;
            }
        });


        settingsPanel.add(fontSizeL);
        settingsPanel.add(fontSizeInput);
        settingsPanel.add(themeLabel);
        settingsPanel.add(themeComboBox);

        JPanel savePanel = new JPanel();
        savePanel.setBackground(panelColor);

        saveBtn = new JButton("Save Settings");
        saveBtn.setBackground(bgColor);
        saveBtn.setForeground(fgColor);

        savePanel.add(saveBtn);

        saveBtn.addActionListener(this);

        settingsFrame.add(settingsPanel, BorderLayout.CENTER);
        settingsFrame.add(savePanel, BorderLayout.SOUTH);
        settingsFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == settingsBtn){
            openSettings();
        }

        if(e.getSource() == saveBtn){
            Config config = new Config();
            String originalTheme = config.getTheme();
            //On save, get Font Size from Settings text Field (sanitized)
            String settingFontSizeInput = fontSizeInput.getText();
            String cleanFSI = settingFontSizeInput.replaceAll("[^0-9]","");
            int fontSizeInt = Integer.parseInt(cleanFSI);


            //On save, get Selected Theme from dropdown
            String selectedTheme = Objects.requireNonNull(themeComboBox.getSelectedItem()).toString();

            //update Config with fontSize and Theme

            config.setFontSize(fontSizeInt);
            config.setTheme(selectedTheme);
            if(!originalTheme.equals(selectedTheme)) {
                menuFrame.dispose();
                settingsFrame.dispose();
                MainMenu mm = new MainMenu();
                mm.openSettings();
            }
        }

        if(e.getSource() == exitBtn){
            System.exit(0);
        }
        if(e.getSource() == newEntry){

            newEntryFrame = new JFrame("New Entry");
            newEntryFrame.setIconImage(logo.getImage());
            newEntryFrame.setLayout(new GridLayout(1, 1));
            newEntryFrame.setSize(500,500);
            newEntryFrame.setLocationRelativeTo(null);

            newEntryPanel = new JPanel();
            newEntryPanel.setLayout(new BorderLayout());
            newEntryPanel.setBorder(BorderFactory.createEmptyBorder(25,40,45,40));
            newEntryPanel.setBackground(panelColor);

            JLabel newEntryLabel = new JLabel("Please select the date for the entry.");
            newEntryLabel.setHorizontalAlignment(JLabel.CENTER);
            newEntryLabel.setForeground(fgColor);
            newEntryLabel.setFont(new Font("Arial", Font.BOLD, 22));

            dateChooser = new JDateChooser();
            dateChooser.setBorder(BorderFactory.createLineBorder(bgColor,140));

            dateChooser.setDateFormatString("yyyy-MM-dd");
            dateChooser.setBackground(bgColor);
            dateChooser.setToolTipText("Enter Date - YYYY-MM-DD");
            dateChooser.getCalendarButton().setBackground(bgColor);



            confirm = new JButton("Confirm");
            confirm.setBackground(bgColor);
            confirm.setForeground(fgColor);
            confirm.setContentAreaFilled(false);
            confirm.setOpaque(true);
            confirm.setPreferredSize(new Dimension(70, 50));
            confirm.addActionListener(this);

            newEntryPanel.add(newEntryLabel,BorderLayout.NORTH);
            newEntryPanel.add(dateChooser,BorderLayout.CENTER);
            newEntryPanel.add(confirm,BorderLayout.SOUTH);
            newEntryFrame.add(newEntryPanel);
            newEntryFrame.setVisible(true);

        }
        if(e.getSource() == confirm){
            if(dateChooser.getDate()!=null){
                String year = String.valueOf(dateChooser.getCalendar().get(Calendar.YEAR));
                String month = String.format("%02d", dateChooser.getCalendar().get(Calendar.MONTH) + 1);
                String day = String.format("%02d", dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH));

                String entriesFilePath;

                Config config = new Config();
                entriesFilePath = config.getFilePath();

                int pageNumber = 1;

                java.io.File dirPath = new java.io.File(entriesFilePath);

                if(dirPath.list() != null) {
                    pageNumber = Objects.requireNonNull(dirPath.list()).length+1;
                }

                new Display(pageNumber, year + "-" + month + "-" + day);
                menuFrame.dispose();
                newEntryFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid date.");
            }
        }

        if(e.getSource() == loadExisting) {
            String date = "";
            LoadEntry loadEntry = new LoadEntry();

            loadEntry.loadEntry(date, true, null,menuFrame);
        }
    }
}
