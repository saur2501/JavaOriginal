package designPatterns.ClassCode;
import java.util.Observable;
import java.util.Observer;

class WeatherBData extends Observable {
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherBData() { }
	
	public void measurementsChanged() {
		setChanged();				//sets changed = true
		notifyObservers();			//if changed is true, then update() all observers in list. Reset Changed.
	}
	
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}
	
	public float getTemperature() {
		return temperature;
	}
	
	public float getHumidity() {
		return humidity;
	}
	
	public float getPressure() {
		return pressure;
	}
}

interface DisplayBElement {
	public void display();
}

class CurrentConditionsBDisplay implements Observer, DisplayBElement {
	Observable observable;
	private float temperature;
	private float humidity;
	
	public CurrentConditionsBDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}
	
	public void update(Observable obs, Object arg) {
		if (obs instanceof WeatherBData) {
			WeatherBData weatherData = (WeatherBData)obs;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}
	}
	
	public void display() {
		System.out.println("Current conditions: " + temperature 
			+ "F degrees and " + humidity + "% humidity");
	}
}

class ForecastBDisplay implements Observer, DisplayBElement {
	private float currentPressure = 29.92f;  
	private float lastPressure;

	public ForecastBDisplay(Observable observable) {
		observable.addObserver(this);
	}

	public void update(Observable observable, Object arg) {
		if (observable instanceof WeatherBData) {
			WeatherBData weatherData = (WeatherBData)observable;
			lastPressure = currentPressure;
			currentPressure = weatherData.getPressure();
			display();
		}
	}

	public void display() {
		System.out.print("Forecast: ");
		if (currentPressure > lastPressure) {
			System.out.println("Improving weather on the way!");
		} else if (currentPressure == lastPressure) {
			System.out.println("More of the same");
		} else if (currentPressure < lastPressure) {
			System.out.println("Watch out for cooler, rainy weather");
		}
	}
}

class HeatIndexBDisplay implements Observer, DisplayBElement {
	float heatIndex = 0.0f;

	public HeatIndexBDisplay(Observable observable) {
		observable.addObserver(this);
	}

	public void update(Observable observable, Object arg) {
		if (observable instanceof WeatherBData) {
			WeatherBData weatherData = (WeatherBData)observable;
			float t = weatherData.getTemperature();
			float rh = weatherData.getHumidity();
			heatIndex = (float)
				(
				(16.923 + (0.185212 * t)) + 
				(5.37941 * rh) - 
				(0.100254 * t * rh) + 
				(0.00941695 * (t * t)) + 
				(0.00728898 * (rh * rh)) + 
				(0.000345372 * (t * t * rh)) - 
				(0.000814971 * (t * rh * rh)) +
				(0.0000102102 * (t * t * rh * rh)) - 
				(0.000038646 * (t * t * t)) + 
				(0.0000291583 * (rh * rh * rh)) +
				(0.00000142721 * (t * t * t * rh)) + 
				(0.000000197483 * (t * rh * rh * rh)) - 
				(0.0000000218429 * (t * t * t * rh * rh)) +
				(0.000000000843296 * (t * t * rh * rh * rh)) -
				(0.0000000000481975 * (t * t * t * rh * rh * rh)));
			display();
		}
	}

	public void display() {
		System.out.println("Heat index is " + heatIndex);
	}
}
class StatisticsBDisplay implements Observer, DisplayBElement {
	private float maxTemp = 0.0f;
	private float minTemp = 200;
	private float tempSum= 0.0f;
	private int numReadings;

	public StatisticsBDisplay(Observable observable) {
		observable.addObserver(this);
	}

	public void update(Observable observable, Object arg) {
		if (observable instanceof WeatherBData) {
			WeatherBData weatherData = (WeatherBData)observable;
			float temp = weatherData.getTemperature();
			tempSum += temp;
			numReadings++;

			if (temp > maxTemp) {
				maxTemp = temp;
			}
 
			if (temp < minTemp) {
				minTemp = temp;
			}

			display();
		}
	}

	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
			+ "/" + maxTemp + "/" + minTemp);
	}
}

public class ObserverPatternBuiltIn {
	public static void main(String[] args) {
		WeatherBData weatherData = new WeatherBData();
		CurrentConditionsBDisplay currentConditions = new CurrentConditionsBDisplay(weatherData);
		StatisticsBDisplay statisticsDisplay = new StatisticsBDisplay(weatherData);
		ForecastBDisplay forecastDisplay = new ForecastBDisplay(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
		HeatIndexBDisplay heatIndexDisplay = new HeatIndexBDisplay(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
