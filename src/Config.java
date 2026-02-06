import java.io.*;

public class Config {

    public String filePath;
    public String fontSize;
    String contents;

    public void readConfigFilepath() {

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

    public void readConfigFontsize() {

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
        readConfigFontsize();
        return Integer.parseInt(fontSize);
    }

    public void setFontSize(int newSize) {
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
//    public Config() throws FileNotFoundException {
//    }

    public String getFilePath() {
        readConfigFilepath();
        return filePath;
    }


}
