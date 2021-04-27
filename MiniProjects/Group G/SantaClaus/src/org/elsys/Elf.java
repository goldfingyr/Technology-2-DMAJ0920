package org.elsys;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ThreadLocalRandom;

public class Elf implements Runnable {
    private int id;
    private SantaClaus santa;
    public Elf(int id, SantaClaus santa) {
        this.id = id;
        this.santa = santa;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Elf %d is knocking at Santa's door\n", id);
            this.santa.processElf(this);
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
