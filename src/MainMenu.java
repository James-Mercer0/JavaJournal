import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.Objects;

public class MainMenu implements ActionListener {

    JFrame menuFrame;
    JPanel welcomePanel;
    JPanel buttonPanel;
    JLabel welcomeLabel;
    JButton loadExisting;
    JButton newEntry;
    JButton exitBtn;

    public MainMenu(){
        menuFrame = new JFrame();
        menuFrame.setLayout(new BorderLayout());
        menuFrame.setSize(1920,1080);
        menuFrame.setTitle("Journal");
        ImageIcon logo = new ImageIcon("imgs/Journal.png");
        menuFrame.setIconImage(logo.getImage());

        welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridLayout(1, 1));
        welcomePanel.setBackground(new Color(42, 42, 51));
        welcomePanel.setBounds(0, 0, 1920, 980);

        welcomeLabel = new JLabel("Journal_2K");
        welcomeLabel.setForeground(new Color(241, 237, 237));
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 89));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomePanel.add(welcomeLabel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(62, 62, 73));
        buttonPanel.setBounds(0, 980, 1920, 100);

        Dimension btnDimension = new Dimension(120, 50);

        loadExisting = new JButton("Load Existing");
        loadExisting.setPreferredSize(btnDimension);
        loadExisting.setContentAreaFilled(false);
        loadExisting.setOpaque(true);
        loadExisting.setBackground(new Color(61, 61, 80));
        loadExisting.setForeground(new Color(241, 237, 237));

        newEntry = new JButton("New Entry");
        newEntry.setPreferredSize(btnDimension);
        newEntry.setContentAreaFilled(false);
        newEntry.setOpaque(true);
        newEntry.setBackground(new Color(61, 61, 80));
        newEntry.setForeground(new Color(241, 237, 237));

        exitBtn = new JButton("Exit");
        exitBtn.setPreferredSize(btnDimension);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setOpaque(true);
        exitBtn.setBackground(new Color(61, 61, 80));
        exitBtn.setForeground(new Color(241, 237, 237));

        loadExisting.addActionListener(this);
        newEntry.addActionListener(this);
        exitBtn.addActionListener(this);

        buttonPanel.add(loadExisting);
        buttonPanel.add(newEntry);
        buttonPanel.add(exitBtn);

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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitBtn){
            System.exit(0);
        }
        if(e.getSource() == newEntry){

            newEntryFrame = new JFrame("New Entry");
            ImageIcon logo = new ImageIcon("imgs/Journal.png");
            newEntryFrame.setIconImage(logo.getImage());
            newEntryFrame.setLayout(new GridLayout(1, 1));
            newEntryFrame.setSize(500,500);
            newEntryFrame.setLocationRelativeTo(null);

            newEntryPanel = new JPanel();
            newEntryPanel.setLayout(new BorderLayout());
            newEntryPanel.setBorder(BorderFactory.createEmptyBorder(25,40,45,40));
            newEntryPanel.setBackground(new Color(61, 61, 80));

            JLabel newEntryLabel = new JLabel("Please select the date for the entry.");
            newEntryLabel.setHorizontalAlignment(JLabel.CENTER);
            newEntryLabel.setForeground(new Color(241, 237, 237));
            newEntryLabel.setFont(new Font("Arial", Font.BOLD, 22));

            dateChooser = new JDateChooser();
            dateChooser.setBorder(BorderFactory.createLineBorder(new Color(61, 61, 80),140));

            dateChooser.setDateFormatString("yyyy-MM-dd");
            dateChooser.setBackground(new Color(61, 61, 80));
            dateChooser.setToolTipText("Enter Date - YYYY-MM-DD");
            dateChooser.getCalendarButton().setBackground(new Color(61, 61, 80));



            confirm = new JButton("Confirm");
            confirm.setBackground(new Color(61, 61, 80));
            confirm.setForeground(new Color(241, 237, 237));
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
