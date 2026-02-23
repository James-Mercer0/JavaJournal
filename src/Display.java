import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Display implements ActionListener {

    int fontSize;
    int page;
    JLabel pageLabel;
    JLabel date;
    JFrame frame;
    JPanel panel;
    JPanel btnPanel;
    JPanel centerPanel;
    JPanel fontPanel;
    JPanel blankPanel;
    JButton prevBtn;
    JButton nextBtn;
    JButton saveBtn;
    JButton loadBtn;
    JButton mainMenuBtn;
    JButton fontPlusBtn;
    JButton fontMinusBtn;
    JTextArea textArea;
    JScrollPane scroll;

    Color bgColor;
    Color fgColor;
    Color panelColor;
    Color textAreaColor;
    Color textCaretColor;

    public Display(int pageNumber, String pageDate) {

        setColors();

        Config config = new Config();
        fontSize = config.getFontSize();

        page = pageNumber;

        frame = new JFrame();

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        prevBtn = new JButton("Prev Page");
        nextBtn = new JButton("Next Page");
        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        mainMenuBtn = new JButton("Main Menu");
        fontPlusBtn = new JButton("+");
        fontMinusBtn = new JButton("-");

        prevBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        loadBtn.addActionListener(this);
        mainMenuBtn.addActionListener(this);
        fontPlusBtn.addActionListener(this);
        fontMinusBtn.addActionListener(this);

        prevBtn.setContentAreaFilled(false);
        prevBtn.setOpaque(true);
        prevBtn.setBackground(bgColor);
        prevBtn.setForeground(fgColor);

        nextBtn.setContentAreaFilled(false);
        nextBtn.setOpaque(true);
        nextBtn.setBackground(bgColor);
        nextBtn.setForeground(fgColor);

        saveBtn.setContentAreaFilled(false);
        saveBtn.setOpaque(true);
        saveBtn.setBackground(bgColor);
        saveBtn.setForeground(fgColor);

        loadBtn.setContentAreaFilled(false);
        loadBtn.setOpaque(true);
        loadBtn.setBackground(bgColor);
        loadBtn.setForeground(fgColor);

        mainMenuBtn.setContentAreaFilled(false);
        mainMenuBtn.setOpaque(true);
        mainMenuBtn.setBackground(bgColor);
        mainMenuBtn.setForeground(fgColor);

        fontPlusBtn.setContentAreaFilled(false);
        fontPlusBtn.setOpaque(true);
        fontPlusBtn.setBackground(bgColor);
        fontPlusBtn.setForeground(fgColor);
        fontPlusBtn.setPreferredSize(new Dimension(45, 40));
        fontPlusBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));

        fontMinusBtn.setContentAreaFilled(false);
        fontMinusBtn.setOpaque(true);
        fontMinusBtn.setBackground(bgColor);
        fontMinusBtn.setForeground(fgColor);
        fontMinusBtn.setPreferredSize(new Dimension(45, 40));
        fontMinusBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));

        pageLabel = new JLabel("Page "+pageNumber,SwingConstants.LEFT);
        pageLabel.setForeground(fgColor);
        pageLabel.setVerticalAlignment(SwingConstants.NORTH);

        date = new JLabel(pageDate);
        date.setForeground(fgColor);
        date.setHorizontalAlignment(SwingConstants.CENTER);
        date.setFont(new Font("Arial", Font.BOLD, 22));

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(pageLabel);
        panel.add(date);

        centerPanel = new JPanel();
        btnPanel = new JPanel();
        fontPanel = new JPanel();
        blankPanel = new JPanel();


        prevBtn.setPreferredSize(new Dimension(150,45));
        nextBtn.setPreferredSize(new Dimension(150,45));
        saveBtn.setPreferredSize(new Dimension(150,45));
        loadBtn.setPreferredSize(new Dimension(150,45));
        mainMenuBtn.setPreferredSize(new Dimension(150,45));

        btnPanel.add(prevBtn);
        btnPanel.add(nextBtn);
        btnPanel.add(saveBtn);
        btnPanel.add(loadBtn);
        btnPanel.add(mainMenuBtn);

        panel.setBackground(panelColor);
        centerPanel.setBackground(panelColor);
        btnPanel.setBackground(panelColor);

        textArea = new JTextArea();
        textArea.setBackground(textAreaColor);
        textArea.setForeground(fgColor);
        textArea.setFont(new Font("Arial", Font.PLAIN, fontSize));
        textArea.setCaretColor(textCaretColor);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10,10,10,10));

//        MutableAttributeSet set = new SimpleAttributeSet(textPane.getParagraphAttributes());

        scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scroll.getVerticalScrollBar().setBackground(panelColor);

        JScrollBar vBar = scroll.getVerticalScrollBar();

        vBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor = bgColor;
            }
        });

        panel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        panel.setLayout(new GridLayout(0, 1));

        fontPanel.setBackground(panelColor);
        fontPanel.setPreferredSize(new Dimension(60,0));
        fontPanel.add(fontPlusBtn);
        fontPanel.add(fontMinusBtn);

        blankPanel.setBackground(panelColor);
        blankPanel.setPreferredSize(new Dimension(60,0));

        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20,0));

        centerPanel.add(scroll,BorderLayout.CENTER);
        centerPanel.add(fontPanel,BorderLayout.EAST);
        centerPanel.add(blankPanel,BorderLayout.WEST);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);

        frame.revalidate();
        frame.pack();

        frame.setTitle("Journal");
        frame.setSize(1920,1080);
        frame.setVisible(true);

        scroll.setBounds(centerPanel.getWidth()/4,20,centerPanel.getWidth()/2,centerPanel.getHeight()-50);
        scroll.setPreferredSize(new Dimension(centerPanel.getWidth()/2,centerPanel.getHeight()/2));

        ImageIcon logo = new ImageIcon("imgs/Journal.png");
        frame.setIconImage(logo.getImage());


        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close the program?", "Close Program?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                            System.exit(0);
                }
            }
        });


        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // When the user releases the mouse button, change Border size surrounding Text area on Resize
                centerPanel.setBorder(BorderFactory.createEmptyBorder(20, (int)((double)centerPanel.getWidth()/3.5), 20,(int)((double)centerPanel.getWidth()/3.5)));
            }
        });

        //Save on Ctrl + S press detected -
        textArea.addKeyListener(new KeyAdapter() {
            boolean ctrl = false;
            boolean sKey = false;

            //toggle off when keys are released
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_CONTROL){
                    ctrl = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_S){
                    sKey = false;
                }
            }

            //detect when keys are pressed
            public void keyPressed(KeyEvent e) {

                //visually re-enable button save on any key press (disabled on SaveEntry method to show that saving was successful)
                saveBtn.setEnabled(true);

                if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                    ctrl = true;
                }

                if (e.getKeyCode() == KeyEvent.VK_S && ctrl) {
                    sKey = true;
                }

                //save when both keys are pressed
                if (ctrl && sKey) {
                    SaveEntry saveEntry = new SaveEntry();
                    try {
                        saveEntry.saveEntry(date.getText(),Integer.parseInt(pageLabel.getText().substring(pageLabel.getText().length()-1)),textArea.getText(),saveBtn);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }

            }
        });
    }

    private void setColors(){
        Config config = new Config();

        String theme = config.getTheme();

        switch(theme) {
            case "Light":
                bgColor = new Color(203, 203, 232);
                fgColor = new Color(41, 40, 40);
                panelColor = new Color(212, 212, 225);
                textAreaColor = new Color(221, 222, 230);
                textCaretColor = Color.BLACK;
                break;
            case "Pastel":
                bgColor = new Color(243, 239, 195);
                fgColor = new Color(60, 64, 71);
                panelColor = new Color(201, 201, 248);
                textAreaColor = new Color(194, 200, 239);
                textCaretColor = Color.BLACK;
                break;
            case "Pink":
                bgColor = new Color(246, 128, 237);
                fgColor = new Color(86, 27, 87);
                panelColor = new Color(237, 160, 217);
                textAreaColor = new Color(244, 189, 242);
                textCaretColor = Color.BLACK;
                break;
            case "Sim":
                bgColor = new Color(20, 58, 67);
                fgColor = new Color(13, 201, 225);
                panelColor = new Color(30, 31, 32);
                textAreaColor = new Color(18, 47, 60);
                textCaretColor = Color.WHITE;
                break;
            case "Matrix":
                bgColor = new Color(1, 23, 1);
                fgColor = new Color(29, 159, 1);
                panelColor = new Color(9, 16, 9);
                textAreaColor = new Color(17, 30, 17);
                textCaretColor = Color.WHITE;
                break;
            case "Midnight":
                bgColor = new Color(20, 20, 30);
                fgColor = new Color(177, 177, 184);
                panelColor = new Color(0, 0, 0);
                textAreaColor = new Color(5, 5, 5);

                textCaretColor = Color.WHITE;
                break;
            default:
                bgColor = new Color(61, 61, 80);
                fgColor = new Color(241, 237, 237);
                panelColor = new Color(42, 42, 57);
                textAreaColor = new Color(48, 48, 60);
                textCaretColor = Color.WHITE;
                break;
        }
    }

    private boolean saveConfirmation(){
        Object[] options = {"Yes","No","Cancel"};

        JOptionPane pane = new JOptionPane();
        pane.setMessage("Would you like to save your current Entry");
        pane.setOptions(options);
        pane.setInitialSelectionValue(JOptionPane.CANCEL_OPTION);

        JDialog dialog = pane.createDialog(frame,"Save current Entry?");
        dialog.setVisible(true);

        if(pane.getValue() == null){
            return false;
        }
        Object selectedOption = pane.getValue();

        if(selectedOption.equals("Yes")){
            SaveEntry saveEntry = new SaveEntry();
            try {
                saveEntry.saveEntry(date.getText(),Integer.parseInt(pageLabel.getText().substring(pageLabel.getText().length()-1)),textArea.getText(),saveBtn);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } else if (selectedOption.equals("Cancel")){
            return false;
        }
        return true;
    }


    private void loadSequential(boolean next){
        Config config;
        config = new Config();

        String path = config.getFilePath();
        int pageNum = Integer.parseInt(pageLabel.getText().substring(pageLabel.getText().length()-1));
        String filename = date.getText() + "_page" + String.format("%04d", pageNum) + ".txt";
        File file = new File(path+filename);
        File dir = new File(path);
        if(file.exists()){
            File[] files = dir.listFiles();

            if(files!=null){
                for(int i=0;i<files.length;i++) {
                    if (filename.equals(files[i].getName())) {
                        int l;

                        if(next){
                            l = i + 1;
                        } else {
                            l = i - 1;
                        }
                        while (l < files.length && l > -1) {
                            if(!saveConfirmation()){
                                return;
                            }
                            if (files[l].getName().endsWith(".txt")) {
                                LoadEntry loadEntry = new LoadEntry();
                                JFrame menuFrame;
                                String date = files[l].getName().substring(0, files[l].getName().indexOf("_"));
                                loadEntry.updatePage(date,files[l].getPath(),false,this,menuFrame=null);
                                return;
                            } else {
                                if(next){
                                    l++;
                                } else {
                                    l--;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "This entry has not yet been saved! Please save and try again.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nextBtn) {
            loadSequential(true);
        }

        if(e.getSource() == prevBtn && page>0) {
            loadSequential(false);
        }

        if(e.getSource() == saveBtn) {
            SaveEntry saveEntry = new SaveEntry();
            try {
                saveEntry.saveEntry(date.getText(),Integer.parseInt(pageLabel.getText().substring(pageLabel.getText().length()-1)),textArea.getText(),saveBtn);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }

        JFrame menuFrame;
        if(e.getSource() == loadBtn) {
            if(!saveConfirmation()){
                return;
            }
            LoadEntry loadEntry = new LoadEntry();
            loadEntry.loadEntry(date.getText(),false,this, menuFrame = null);
        }

        if(e.getSource() == mainMenuBtn){
            if(JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit to menu?", "Quit to menu?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                if(!saveConfirmation()){
                    return;
                }
                MainMenu mainMenu = new MainMenu();
                frame.dispose();
            }
        }

        if(e.getSource() == fontPlusBtn){
            fontSize++;
            textArea.setFont(new Font("Arial", Font.PLAIN, fontSize));
            Config config = new Config();
            config.setFontSize(fontSize);
        }

        if(e.getSource() == fontMinusBtn){
            fontSize--;
            textArea.setFont(new Font("Arial", Font.PLAIN, fontSize));
            Config config = new Config();
            config.setFontSize(fontSize);
        }

    }


}


