package sleepingBarber;

public class Customer implements Runnable {
    
	private int customerNum;
    private BarberShop shop;
 
    public Customer(BarberShop shop) {
        this.shop = shop;
    }
 
    public int getCustomerNum() {		
        return customerNum;
    }
 
    public void setcustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }
  
    @Override
    public void run() {
        newCustomer();
    }
    
    private synchronized void newCustomer() {
        shop.newCustomer(this);
    }
}