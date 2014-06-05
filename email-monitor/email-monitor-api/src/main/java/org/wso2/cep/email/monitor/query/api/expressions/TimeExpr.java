package org.wso2.cep.email.monitor.query.api.expressions;


public class TimeExpr extends Expression {

    private int year;
    private int month;
    private int week;
    private int day;
    private int hour;
    private int miniute;
    private int second;
    private int milisecond;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMiniute() {
        return miniute;
    }

    public void setMiniute(int miniute) {
        this.miniute = miniute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMilisecond() {
        return milisecond;
    }

    public void setMilisecond(int milisecond) {
        this.milisecond = milisecond;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (year > 0) {
            stringBuffer.append(year + " years ");
        }
        if (month > 0) {
            stringBuffer.append(month + " months ");
        }
        if (week > 0) {
            stringBuffer.append(week + " weeks ");
        }
        if (day > 0) {
            stringBuffer.append(day + " days");
        }
        if (hour > 0) {
            stringBuffer.append(hour + " hours ");
        }
        if (miniute > 0) {
            stringBuffer.append(miniute + " miniutes ");
        }
        if (second > 0) {
            stringBuffer.append(second + " seconds ");
        }
        if (milisecond > 0) {
            stringBuffer.append(milisecond + " miliseconds ");
        }
        return stringBuffer.toString();
    }
}
