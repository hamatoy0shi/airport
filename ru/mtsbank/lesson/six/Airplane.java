package ru.mtsbank.lesson.six;

public class Airplane {

    private final int number;

    public Airplane(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void informGotToRunway(int runwayNum) {
        System.out.println("Самолёт " + number + " выруливает на полосу " + runwayNum);
    }

    public void informTookOff() {
        System.out.println("Самолёт " + number + " взлетел");
    }

}
