package kata;

public class SmartFridge implements Fridge{
    public String currentDate;
    private SmartFridgeService smartFridgeService;

    public SmartFridge(SmartFridgeService smartFridgeService) {
        this.smartFridgeService = smartFridgeService;
    }

    @Override
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public void signalFridgeDoorOpened() {
        smartFridgeService.openDoor();
    }

    @Override
    public void signalFridgeDoorClosed() {
        smartFridgeService.closeDoor();
    }

    @Override
    public void scanAddedItem(String name, String expiryDate, String status) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void scanRemovedItem(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void simulateDayOver() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void showDisplay() {
        throw new UnsupportedOperationException();
    }
}
