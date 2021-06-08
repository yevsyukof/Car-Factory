package ru.nsu.yevsyukof.model.factory.dealers;

import ru.nsu.yevsyukof.CarFactory;
import ru.nsu.yevsyukof.model.factory.Delay;
import ru.nsu.yevsyukof.model.factory.products.car.Car;
import ru.nsu.yevsyukof.model.factory.warehouses.Storage;

public class Dealer extends Thread {

    private final Storage<Car> carStorage;

    private final Delay dealerDelay;

    public Dealer(Storage<Car> carStorage, Delay dealerDelay) {
        this.carStorage = carStorage;
        this.dealerDelay = dealerDelay;
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                Car car = carStorage.getProduct();
                CarFactory.getInstance().getLogger().info(
                        "Получение машины дилером: Dealer {}: Car {} (Engine: {} Body: {} Accessory: {}) ",
                        Thread.currentThread().getId(), car.getProductID(), car.getEngine().getProductID(),
                        car.getBody().getProductID(), car.getAccessory().getProductID());

                synchronized (carStorage) {
                    carStorage.notifyAll();
                }

                Thread.sleep(1000L * dealerDelay.getDelay());
            }
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " was interrupted");
        }

    }
}
