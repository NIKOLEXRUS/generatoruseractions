package model;

import java.time.LocalDate;

public class UserAction {

    private User user;
    private String Position;
    private String Status;
    private String DepartamentOne;
    private String DepartamentTwo;
    private String DepartamentThree;
    private LocalDate StartDate;
    private LocalDate EndDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDepartamentOne() {
        return DepartamentOne;
    }

    public void setDepartamentOne(String departamentOne) {
        DepartamentOne = departamentOne;
    }

    public String getDepartamentTwo() {
        return DepartamentTwo;
    }

    public void setDepartamentTwo(String departamentTwo) {
        DepartamentTwo = departamentTwo;
    }

    public String getDepartamentThree() {
        return DepartamentThree;
    }

    public void setDepartamentThree(String departamentThree) {
        DepartamentThree = departamentThree;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }
}
