package org.elsys;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.System.out;

public class SantaClaus {

    private static final Random rand = new Random();

    private static final CyclicBarrier reindeers = new CyclicBarrier(9);
    private static final CyclicBarrier elves = new CyclicBarrier(3);
    // use a fair semaphore for Santa to prevent that a second group
    // of elves might get Santas attention first if the reindeer are
    // waiting and Santa is consulting with a first group of elves.
    private static final Semaphore santa = new Semaphore(1, true);

    public SantaClaus() {
        int reindeers = 0, elves = 0;
        try {
            while(true) {
                Thread.sleep(1000);
                if(rand.nextInt(2) % 2 == 0) {
                    new Thread(new Reindeer(++reindeers, this)).start();
                } else {
                    new Thread(new Elf(++elves, this)).start();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void processReindeer(Reindeer reindeer) throws BrokenBarrierException, InterruptedException {
        reindeers.await();
        santa.acquire();
        this.prepareSleigh(reindeer);
        santa.release();
    }

    public void processElf(Elf elf) throws BrokenBarrierException, InterruptedException {
        elves.await();
        santa.acquire();
        this.helpElves(elf);
        santa.release();
    }

    private void prepareSleigh(Reindeer reindeer) throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(0, 1000));
        System.out.printf("Santa hitched Reindeer %d\n", reindeer.getId());
    }

    private void helpElves(Elf elf) throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(0, 1000));
        System.out.printf("Santa helped Elf %d\n", elf.getId());
    }

    public static void main(String[] args) {
        new SantaClaus();
    }
}
