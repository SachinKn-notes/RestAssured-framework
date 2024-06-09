package PojoClasses;

import java.util.ArrayList;

public class Root {
    private Dashboard dashboard;
    private ArrayList<Course> courses;

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Root{" +
                "dashboard=" + dashboard +
                ", courses=" + courses +
                '}';
    }
}
