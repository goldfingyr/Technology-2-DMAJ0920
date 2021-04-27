package org.elsys;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ThreadLocalRandom;

public class Reindeer implements Runnable {
    private int id;
    private SantaClaus santa;
    public Reindeer(int id, SantaClaus santa) {
        this.id = id;
        this.santa = santa;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Reindeer %d got back from vacation\n", id);
            this.santa.processReindeer(this);
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
