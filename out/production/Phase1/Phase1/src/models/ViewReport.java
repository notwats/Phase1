package models;

import java.time.LocalDate;
import java.util.ArrayList;

// just for a stupid table

public class ViewReport {

    LocalDate eachDay;
    Integer viewNum = 0;
    Integer likNum = 0;


    public static ArrayList<ViewReport> sortByDay(ArrayList<LocalDate> viewlist, ArrayList<LocalDate> likelist, LocalDate creationDate) {
        ArrayList<ViewReport> ret = new ArrayList<>();
        LocalDate now = LocalDate.now();
        Integer today = now.getDayOfYear();
        System.out.println(today);
        System.out.println(creationDate.getDayOfYear());
        for (int i = creationDate.getDayOfYear(); i <= today; i++) {
            ViewReport viewReport = new ViewReport();
            for (LocalDate dd : viewlist) {
                if (dd.getDayOfYear() == i) {
                    viewReport.viewNum += 1;
                    viewReport.eachDay = dd;
                }
            }
            for (LocalDate dd : likelist) {
                if (dd.getDayOfYear() == i) {
                    viewReport.likNum += 1;
                    viewReport.eachDay = dd;
                }
            }
            if (viewReport.eachDay != null)
                ret.add(viewReport);
        }
        return ret;
    }

    @Override
    public String toString() {
        return "ViewReport{" +
                "Day=" + eachDay +
                ", viewNum=" + viewNum +
                ", likNum=" + likNum +
                '}';
    }
}
