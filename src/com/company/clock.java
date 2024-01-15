package com.company;

enum State {
    DISPLAY_TIME, DISPLAY_DATE, CHANGE_TIME, CHANGE_DATE
}

class Clock {
    private Time theTime = new Time();
    private Date theDate = new Date();
    private State state = State.DISPLAY_TIME;

    public String changeMode() {

        switch (state) {
            case DISPLAY_TIME:
                state = State.DISPLAY_DATE;
                return theDate.showDate();

            case DISPLAY_DATE:
                state = State.DISPLAY_TIME;
                return theTime.showTime();
            default:
                return "Invalid changeMode";
        }
    }

    public String ready() {
        switch (state) {
            case DISPLAY_TIME:
                state = State.CHANGE_TIME;
                return "Time ready to be set!";

            case DISPLAY_DATE:
                state = State.CHANGE_DATE;
                return "Date ready to be set!";

            default:
                return "Invalid ready";
        }
    }

    public String set(int p1, int p2, int p3) {
        switch (state) {
            case CHANGE_TIME:
                state = State.DISPLAY_TIME;
                return "Time set to: " + theTime.timeSet(p1, p2, p3);

            case CHANGE_DATE:
                state = State.DISPLAY_DATE;
                return "Date set to: " + theDate.dateSet(p1, p2, p3);

            default:
                return "Invalid set";
        }
    }
}

class Time {
    private int TheHour = 0;
    private int TheMinute = 0;
    private int TheSecond = 0;

    public String timeSet(int hour, int minute, int second) {
        if(hour < 0 || minute < 0 || second < 0){
            return "Error! Negative value";
        }
        if (hour>24){
            return "Error! Hour can not be higher then 24";
        }
        else if(minute > 59) {
            return "Error! Minute can not be higher then 59";
        }
        else if(second > 59) {
            return "Error! Second can not be higher then 59";
        }
        TheHour = hour;
        TheMinute = minute;
        TheSecond = second;
        return "Hour: " + hour + " Minute: " + minute + " Second: " + second;
    }

    public String showTime() {
        return "Hour: " + TheHour + " Minute: " + TheMinute + " Second: " + TheSecond;
    }
}

class Date {
    private int TheYear = 2024;
    private int TheMonth = 1;
    private int TheDay = 1;

    public String dateSet(int year, int month, int day) {
        if(year <= 0 || month <= 0 || day <= 0){
            return "Error! Negative or Zero value";
        }

       if(month > 12) {
            return "Error! Month can not be higher then 12";
        }
        else if(day > 31) {
            return "Error! Day can not be higher then 31";
        }
        TheYear = year;
        TheMonth = month;
        TheDay = day;
        return "Year: " + year + " Month: " + month + " Day: " + day;
    }

    public String showDate() {
        return "Year: " + TheYear + " Month: " + TheMonth + " Day: " + TheDay;
    }
}
