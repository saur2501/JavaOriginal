package designPatterns.derek;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class ObserverPatternDemo {
	public static void main(String[] args){
        StockGrabber stockGrabber = new StockGrabber();					// Create the ISubject object. It will handle updating all observers as well as deleting and adding them
        StockObserver observer1 = new StockObserver(stockGrabber);		// Create an Observer that will be sent updates from ISubject
        stockGrabber.setIBMPrice(197.00);
        stockGrabber.setAAPLPrice(677.60);
        stockGrabber.setGOOGPrice(676.40);
        StockObserver observer2 = new StockObserver(stockGrabber);
        stockGrabber.setIBMPrice(197.00);
        stockGrabber.setAAPLPrice(677.60);
        stockGrabber.setGOOGPrice(676.40);
        // Delete one of the observers
        // stockGrabber.unregister(observer2);
        stockGrabber.setIBMPrice(197.00);
        stockGrabber.setAAPLPrice(677.60);
        stockGrabber.setGOOGPrice(676.40);
        // Create 3 threads using the Runnable interface. GetTheStock implements Runnable, so it doesn't waste its one extend-able class option
        Runnable getIBM = new GetTheStock(stockGrabber, 2, "IBM", 197.00);
        Runnable getAAPL = new GetTheStock(stockGrabber, 2, "AAPL", 677.60);
        Runnable getGOOG = new GetTheStock(stockGrabber, 2, "GOOG", 676.40);
        // Call for the code in run to execute
        new Thread(getIBM).start();
        new Thread(getAAPL).start();
        new Thread(getGOOG).start();
    }
}

// This interface handles adding, deleting and updating all observers
interface ISubject {
    public void register(IObserver o);
    public void unregister(IObserver o);
    public void notifyObserver();
}

// Uses the Subject interface to update all Observers
class StockGrabber implements ISubject{
    private ArrayList<IObserver> observers;
    private double ibmPrice;
    private double aaplPrice;
    private double googPrice;
    public StockGrabber(){
        // Creates an ArrayList to hold all observers
        observers = new ArrayList<IObserver>();
    }
    public void register(IObserver newObserver) {
        // Adds a new observer to the ArrayList
        observers.add(newObserver);
    }
    public void unregister(IObserver deleteObserver) {
        int observerIndex = observers.indexOf(deleteObserver);					// Get the index of the observer to delete
        System.out.println("Observer " + (observerIndex+1) + " deleted");		// Print out message (Have to increment index to match)
        observers.remove(observerIndex);										// Removes observer from the ArrayList
    }
    public void notifyObserver() {
        for(IObserver observer : observers){									// Cycle through all observers and notifies them of price changes
            observer.update(ibmPrice, aaplPrice, googPrice);
        }
    }
    // Change prices for all stocks and notifies observers of changes
    public void setIBMPrice(double newIBMPrice){
        this.ibmPrice = newIBMPrice;
        notifyObserver();
    }
    public void setAAPLPrice(double newAAPLPrice){
        this.aaplPrice = newAAPLPrice;
        notifyObserver();
    }
    public void setGOOGPrice(double newGOOGPrice){
        this.googPrice = newGOOGPrice;
        notifyObserver();
    }
}

interface IObserver {
	public void update(double ibmPrice, double aaplPrice, double googPrice);					// The Observers update method is called when the Subject changes
}

class StockObserver implements IObserver {														// Represents each Observer that is monitoring changes in the subject
    private double ibmPrice;
    private double aaplPrice;
    private double googPrice;
    private static int observerIDTracker = 0;													//Static used as a counter
    private int observerID;																		//Used to track the observers
    private ISubject stockGrabber;
    public StockObserver(ISubject stockGrabber){												//Will hold reference to the StockGrabber object
        this.stockGrabber = stockGrabber;														//Store the reference to the stockGrabber object so I can make calls to its methods
        this.observerID = ++observerIDTracker;													//Assign an observer ID and increment the static counter
        System.out.println("New Observer " + this.observerID);									//Message notifies user of new observer
        stockGrabber.register(this);															//Add the observer to the ISubjects ArrayList
    }
    public void update(double ibmPrice, double aaplPrice, double googPrice) {					//Called to update all observers
        this.ibmPrice = ibmPrice;
        this.aaplPrice = aaplPrice;
        this.googPrice = googPrice;
        printThePrices();
    }
    public void printThePrices(){
        System.out.println(observerID + "\nIBM: " + ibmPrice + "\nAAPL: " +
                aaplPrice + "\nGOOG: " + googPrice + "\n");
    }
}

class GetTheStock implements Runnable{
    // Could be used to set how many seconds to wait in Thread.sleep() below private int startTime;
    private String stock;
    private double price;
    private ISubject stockGrabber;									//Will hold reference to the StockGrabber object
    public GetTheStock(ISubject stockGrabber, int newStartTime, String newStock, double newPrice){
        this.stockGrabber = stockGrabber;							//Store the reference to the stockGrabber object so I can make calls to its methods
        stock = newStock;											//startTime = newStartTime;  Not used to have variable sleep time
        price = newPrice;
    }
    public void run(){
        for(int i = 1; i <= 20; i++){
            try{
                Thread.sleep(2000);									//Sleep for 2 seconds. Use Thread.sleep(startTime * 1000); to make sleep time variable
            }
            catch(InterruptedException e)
            {}
            double randNum = (Math.random() * (.06)) - .03;			//Generates a random number between -.03 and .03
            DecimalFormat df = new DecimalFormat("#.##");			//Formats decimals to 2 places
            price = Double.valueOf(df.format((price + randNum)));	//Change the price and then convert it back into a double
            if(stock == "IBM") ((StockGrabber) stockGrabber).setIBMPrice(price);
            if(stock == "AAPL") ((StockGrabber) stockGrabber).setAAPLPrice(price);
            if(stock == "GOOG") ((StockGrabber) stockGrabber).setGOOGPrice(price);
            System.out.println(stock + ": " + df.format((price + randNum)) +
                    " " + df.format(randNum));
            System.out.println();
        }
    }
}