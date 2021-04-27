import java.util.concurrent.*;

public class SleepingBarber extends Thread {
    public static Semaphore customers = new Semaphore(0);
    public static Semaphore barber = new Semaphore(0);
    public static Semaphore accessSeats = new Semaphore(1);

    public static final int CHAIRS = 5;
 	public static int numberOfFreeSeats = CHAIRS;

class Customer extends Thread {  // Customer threads
	
	//  Id for each customer and boolean value that is used int the waiting loop
	
	 boolean notCut = true;  
	 int id;
    
	 public Customer(int i) {
		 id = i;
	 }

	 public void run() {   
		 while (notCut) {
			 try {
				 accessSeats.acquire(); // Accesing the seats
				 if (numberOfFreeSeats > 0) { // looks for available seats and seats down a customer
			        System.out.println("Customer " + this.id + " just sat down.");
			        numberOfFreeSeats--;
			        customers.release(); // Customer sitting down in the seat
			        accessSeats.release(); // Informs the barber that the customer sat down
			        try {
			        barber.acquire(); // Acquiring the barber
			        notCut = false;
			        this.getHaircut(); // Cutting hair
			        } catch (InterruptedException ex) {
			        	//empty
			        }
			     }   
			     else {  // If there are not available seats the customers leaves
			        System.out.println("No more free seats. Customer " + this.id + " has left the barbershop.");
			        accessSeats.release();
			        notCut=false;
			     }
			 }
		     catch (InterruptedException ex) {
		    	  //empty
		     }
		 }
	 }
  
	 // Method for getting a haircut
	 
	 public void getHaircut() {
		 System.out.println("Customer " + this.id + " is getting his hair cut");
		 try { 
	    	 sleep(2050); 
		 } catch (InterruptedException ex) {
	    	//empty
	     }
	 }
}

class Barber extends Thread { // Barber thread
	
	public Barber() {
		//empty
	}
  
	public void run() {
		while(true) {  // Infinite loop
			try {
				customers.acquire(); // Looking for a customer, if no one is available the barber goes to sleep
			    accessSeats.release();
			    numberOfFreeSeats++;
			    barber.release(); // The barber is ready to cut hair again
			    accessSeats.release();
			    this.cutHair(); // The hair is being cut
			    
		    } catch (InterruptedException ex) {
		    	//empty
		    }
		}
  }
   
  public void cutHair(){
	  System.out.println("The barber is cutting hair");
	  try {
		  sleep(5000);
	  } catch (InterruptedException ex){
    	//empty
	  }
  	}
}       
  
  public static void main(String args[]) {
	  SleepingBarber barberShop = new SleepingBarber(); // Creates a new barbershop and starts the process
	  barberShop.start();
  }
  
  public void run(){   
	  Barber barber = new Barber();
	  barber.start();
  
	  for (int i=1; i<11; i++) {
		  Customer customer = new Customer(i);  // Creates new customers (up to 10) and starts the process
		  customer.start();
		  
		  try {
			sleep(2000);
		  } catch(InterruptedException ex) {
			//empty
		  };
	  }
  } 
}