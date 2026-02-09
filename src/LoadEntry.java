import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

public class LoadEntry {

    JFrame selectFrame;

    int page;

    public void loadEntry(String date,boolean fromMenu,Display existingDisplay,JFrame menuFrame) {
        String loadPath;
        JFileChooser selectFile = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        selectFile.setFileFilter(filter);
        selectFile.setDialogTitle("Load a journal page");
        selectFile.setCurrentDirectory(new File(System.getProperty("user.dir") + FileSystems.getDefault().getSeparator() +"entries"));
        selectFrame = new JFrame();
        ImageIcon logo = new ImageIcon("imgs/Journal.png");
        selectFrame.setIconImage(logo.getImage());
        int returnVal = selectFile.showOpenDialog(selectFrame);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            loadPath = selectFile.getSelectedFile().getPath();
            updatePage(date, loadPath, fromMenu, existingDisplay, menuFrame);
        }

    }

    public void updatePage(String date,String loadPath,boolean fromMenu,Display existingDisplay,JFrame menuFrame) {

        String contents;

        try (BufferedReader br = new BufferedReader(new FileReader(loadPath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            contents = sb.toString();
        } catch (IOException ie) {
            throw new RuntimeException(ie);
        }
        page = Integer.parseInt(contents.substring(0, contents.indexOf("|")));
        int s = contents.indexOf("||");

        String afterFirstSeparator = contents.substring(s+2);

        int s2 = afterFirstSeparator.indexOf("||");

        String afterSecondSeparator = afterFirstSeparator.substring(s2+2);


        if(fromMenu) {
            Display display = new Display(page, date);
            display.pageLabel.setText("Page " + page);
            display.date.setText(afterFirstSeparator.substring(0,afterFirstSeparator.indexOf("||")));
            display.textArea.setText(afterSecondSeparator);
            display.textArea.setCaretPosition(0);
            menuFrame.dispose();
        } else {
            existingDisplay.pageLabel.setText("Page " + page);
            existingDisplay.date.setText(afterFirstSeparator.substring(0,afterFirstSeparator.indexOf("||")));
            existingDisplay.textArea.setText(afterSecondSeparator);
            existingDisplay.textArea.setCaretPosition(0);
        }
    }
}
