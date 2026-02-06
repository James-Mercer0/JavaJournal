import java.time.*;

public class JournalPage {

    int pageNumber;
    String pageDate;


    JournalPage(int pageNumber) {
        this.pageNumber = pageNumber;
        this.pageDate = LocalDate.now().getYear()+"-"+String.format("%02d",LocalDate.now().getMonthValue())+"-"+String.format("%02d",LocalDate.now().getDayOfMonth());
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public String getPageDate() {
        return this.pageDate;
    }
}
