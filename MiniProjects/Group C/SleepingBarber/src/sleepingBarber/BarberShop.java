package sleepingBarber;

import java.util.LinkedList;
import java.util.List;

public class BarberShop {
	
	private int noOfChairs;
	private boolean isBarberAvailable = true;
    private List<Customer> customerQueue;
     
    public BarberShop(int noOfChairs) {
        this.noOfChairs = noOfChairs;
        //using linked list for customer queue
        customerQueue = new LinkedList<Customer>();
    }
 
    //method for barber to cut customer's hair
    public void cutCustomer() {
        Customer customer;
        
        //synchronized block on customerQue
        synchronized (customerQueue) {					
        	//while no one is in the queue the barber is waiting 
        	while(customerQueue.size() == 0) {
        		System.out.println("Barber is waiting for the customer");
                try {
                    customerQueue.wait();								
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //use casting so we can remove first in line from the linked list 
            customer = (Customer)((LinkedList<Customer>)customerQueue).poll();
            System.out.println("Customer " + customer.getCustomerNum() + " notifies the barber");	
        }
        
        //print out cutting of the hair
        try {
        	//while barber is cutting customer's hair he is unavailable
        	isBarberAvailable = false; 
            System.out.println("Barber is cutting " + customer.getCustomerNum() + " customer's hair");
            //cutting of the hair takes some time (6000ms)
        	Thread.sleep(6000);	
        	System.out.println(customer.getCustomerNum() + " customer's hair has been cut");
        	//after the cutting is done the barber is available again
            isBarberAvailable = true;
        }
        catch(InterruptedException e) {
        	e.printStackTrace();
        }  
    }
    
    //method for adding new customer to queue
    public void newCustomer(Customer customer) {
    	//another synchronized block on customerQue
        synchronized (customerQueue) {
        	//check if there are any available waiting chairs
            if(customerQueue.size() == noOfChairs) {
            	//if not the customer will leave the shop and his thread will be ended 
                System.out.println("No chair available, customer " + customer.getCustomerNum() + " leaves the shop");
                return;
            }
            //if there are available chair check if barber is available
            else if (isBarberAvailable) {
            	//if he is notify him so the customer can get a haircut
            	((LinkedList<Customer>)customerQueue).add(customer);
				customerQueue.notify();
			}
            //if he is not available customer will proceed to queue 
            else {														
            	((LinkedList<Customer>)customerQueue).add(customer);
            	System.out.println("Barber is busy so customer " + customer.getCustomerNum() + " goes to waiting room");
            }
        }
    }
}
