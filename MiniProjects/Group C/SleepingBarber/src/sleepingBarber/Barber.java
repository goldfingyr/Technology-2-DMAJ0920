package sleepingBarber;

public class Barber implements Runnable {
    
	BarberShop shop;
 
    public Barber(BarberShop shop) {
        this.shop = shop;
    }
    
    @Override
    public void run() {
        while(true) {
            shop.cutCustomer();
        }
    }
}