import javax.swing.*;
import java.io.*;

public class Config {

    public String filePath;
    public String fontSize;
    String contents;

    public void readConfigFilepath() {
        try {
            setUpConfig();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(BufferedReader br = new BufferedReader(new FileReader("config/config.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                if(line.contains("filepath")) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
                line = br.readLine();
            }
            String configFilePath = sb.toString().replace(" ","");

            filePath = configFilePath.substring(10,configFilePath.length()-3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readConfigFontsize(){
        try {
            setUpConfig();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(BufferedReader br = new BufferedReader(new FileReader("config/config.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                if(line.contains("fontsize")) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
                line = br.readLine();
            }
            String configFontsize = sb.toString().replace(" ","");

            fontSize = configFontsize.substring(9,configFontsize.length()-2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getFontSize() {
        try {
            setUpConfig();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        readConfigFontsize();
        return Integer.parseInt(fontSize);
    }

    public void setFontSize(int newSize) {
        try {
            setUpConfig();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader br = new BufferedReader(new FileReader("config/config.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                if (line.contains("fontsize")) {
                    sb.append("fontsize = ").append(newSize);
                    sb.append(System.lineSeparator());
                } else{
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
                line = br.readLine();
            }
            contents = sb.toString();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("config/config.txt"))) {
                bw.write(contents);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFilePath() {
        try {
            setUpConfig();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        readConfigFilepath();
        return filePath;
    }

    /**
     * setUpConfig - Check for config file. If not found, create new folder/config file.
     */
    public void setUpConfig() throws FileNotFoundException {

        try (FileReader configFile = new FileReader("config/config.txt")) {
            //Config exists
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Config file or folder not found - creating new Config");
            File dir = new File("config/");
            if(!dir.exists()) {
                if(dir.mkdir())
                {
                    JOptionPane.showMessageDialog(null, "New Folder created! Saving new config!");
                } else {
                    JOptionPane.showMessageDialog(null, "error - unable to create folder! " + e.getMessage());
                }
            }

            FileWriter fw;
            try {
                fw = new FileWriter("config/config.txt");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                String defaultConfig = "\nJournal pages location:\nfilepath = `.\\entries\\'\n\nfontsize = 25";
                bw.write(defaultConfig);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            }

    }
}
