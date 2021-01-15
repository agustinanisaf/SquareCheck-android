package com.squarecheck.student.model;

import com.squarecheck.R;
import com.squarecheck.shared.model.PresenceModel;
import com.squarecheck.shared.model.ScheduleModel;
import com.squarecheck.shared.util.DateUtil;

public class NotificationPresenceItem {
    private String subjectName;
    private String time;
    private String status;
    private int color;

    public static NotificationPresenceItem fromPresence(ScheduleModel schedule, PresenceModel data) {
        NotificationPresenceItem item = new NotificationPresenceItem();
        item.subjectName = schedule.getSubject().getName();
        item.time = DateUtil.getTime(data.getTime());
        switch (data.getStatus()) {
            case "hadir":
                item.status = "Hadir Tepat Waktu";
                item.color = R.color.hadir;
                break;
            case "telat":
                item.status = "Hadir Terlambat";
                item.color = R.color.telat;
                break;
            default:
                break;
        }
        return item;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
