package ru.mtsbank.lesson.six;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Airplane> blockingQueue = new ArrayBlockingQueue<>(10);

        ArrayList<Runway> runwayList = new ArrayList<>();
        ArrayList<Airplane> airplaneList = new ArrayList<>();
        ArrayList<Thread> runwayThreadList = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            runwayList.add(new Runway(blockingQueue, i));
        }

        for (int i = 1; i < 11; i++) {
            airplaneList.add(new Airplane(i));
        }

        for (Airplane airplane : airplaneList) {
            try {
                blockingQueue.put(airplane);
            } catch (InterruptedException e) {
                System.out.println("Процесс был прерван");
            }
        }

        for (Runway runway : runwayList) {
            Thread runwayThread = new Thread(runway);
            runwayThreadList.add(runwayThread);
            runwayThread.start();
        }

        for (Thread thread : runwayThreadList) {
            thread.join();
        }

        System.out.println("Все самолеты взлетели");
    }

}
