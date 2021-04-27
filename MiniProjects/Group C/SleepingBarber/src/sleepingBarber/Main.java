package sleepingBarber;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String args[]){
		//customerNum serves for tracking customers
		int customerNum = 1;
		
		//n waiting chairs so we let the user input how many waiting chairs there will be in barber shop
		Scanner sc = new Scanner(System.in);
		System.out.println("How many waiting chairs there will be in the barber shop: ");
		int noOfChairs = sc.nextInt();
		sc.close();
		
		//creation of barber shop and barber thread using barber class as runnable    	
    	BarberShop shop = new BarberShop(noOfChairs); 	
        Barber barber = new Barber(shop);	
        Thread barberThread = new Thread(barber);
        barberThread.start();
       
        //infinite loop for creating new customer threads using customer class as runnable
        while(true) {								
            Customer customer = new Customer(shop);
            Thread customerThread = new Thread(customer);
            
            //setting the tracking number to new customer 
            customer.setcustomerNum(customerNum++);
            customerThread.start();    
            
            //random sleep time(between 2000 ms and 5000 ms) between creating new customers 
            try {
            	Random rand = new Random();
				int sleep = rand.nextInt(2000)+3000;
            	Thread.sleep(sleep);								
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
