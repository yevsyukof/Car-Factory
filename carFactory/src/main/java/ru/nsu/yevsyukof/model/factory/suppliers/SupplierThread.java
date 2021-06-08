package ru.nsu.yevsyukof.model.factory.suppliers;

import ru.nsu.yevsyukof.model.factory.Delay;
import ru.nsu.yevsyukof.model.factory.products.IdentifiableProduct;
import ru.nsu.yevsyukof.model.factory.warehouses.Storage;

/* По умолчанию в классе Thread уже есть поле "Runnable target", в нем хранится
 *  выполняемая этим потоком задача */

abstract class SupplierThread<SuppliedProductType extends IdentifiableProduct> extends Thread {

    protected final Storage<SuppliedProductType> destinationStorage;

    protected final Delay supplierDelay;

    public SupplierThread(Storage<SuppliedProductType> destinationStorage, Delay supplierDelay, String threadName) {
        super(threadName);
        this.destinationStorage = destinationStorage;
        this.supplierDelay = supplierDelay;
    }

    public abstract SuppliedProductType createProduct();

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                destinationStorage.storeProduct(createProduct());

                Thread.sleep(1000L * supplierDelay.getDelay());
            }
        } catch (InterruptedException ignored) { }
    }
}
