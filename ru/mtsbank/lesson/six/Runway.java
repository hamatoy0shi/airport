package ru.mtsbank.lesson.six;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Runway implements Runnable{

    private final int number;

    private BlockingQueue<Airplane> blockingQueue = null;

    public Runway(BlockingQueue<Airplane> blockingQueue, int number) {
        this.blockingQueue = blockingQueue;
        this.number = number;
    }

    private void informNewAirplane(int airplaneNumber) {
        System.out.println("Полоса " + number +  " приняла самолёт " + airplaneNumber);
    }

    private void informGotFree() {
        System.out.println("Полоса " + number + " освободилась");
    }

    @Override
    public void run() {
        while (!blockingQueue.isEmpty()) {
            try {
                Airplane airplane = blockingQueue.take();
                airplane.informGotToRunway(number);
                informNewAirplane(airplane.getNumber());
                TimeUnit.SECONDS.sleep(1);
                airplane.informTookOff();
                informGotFree();
            } catch (InterruptedException e) {
                System.out.println("Работа полосы была прервана");
            }
        }
    }

}
