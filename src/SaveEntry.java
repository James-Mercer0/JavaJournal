import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveEntry {

    public void saveEntry(String date,int page,String textArea, JButton saveBtn) throws FileNotFoundException {
        Config config = new Config();

        String filePath = config.getFilePath();
        try (
                PrintWriter saveFile = new PrintWriter(filePath + date + "_page" + String.format("%04d", page) + ".txt")) {
//                System.out.println(filePath);
            saveFile.println(String.format("%04d", page) + "||" + date + "||" + textArea);
        } catch (
                FileNotFoundException exception) {
                    JOptionPane.showMessageDialog(null, "Path/Folder not found - creating new Folder");
                    if(new File(filePath).mkdir()){
                        JOptionPane.showMessageDialog(null, "New Folder created! Saving file!");
                        PrintWriter saveFile = new PrintWriter(filePath + date + "_page" + String.format("%04d", page) + ".txt");
                        saveFile.println(String.format("%04d", page) + "||" + date + "||" + textArea);
                    } else {
                        JOptionPane.showMessageDialog(null, "error - unable to create folder!");
                    }


        }
//        System.out.println("Saved!");
        saveBtn.setEnabled(false);
    }
}
