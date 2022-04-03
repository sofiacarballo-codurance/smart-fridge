package kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SmartFridgeShould {
    private SmartFridge fridge;
    private SmartFridgeService smartFridgeService;
    private SmartFridgeRepository smartFridgeRepository;

    @BeforeEach
    void setUp() {
        smartFridgeService = mock(SmartFridgeService.class);
        smartFridgeRepository = mock(SmartFridgeRepository.class);
        fridge = new SmartFridge(smartFridgeService, smartFridgeRepository);
    }

    @Test
    void
    set_fridge_date_to_current_date() {
        fridge.setCurrentDate("03/04/2022");
        var result = fridge.currentDate;

        assertEquals(result, "03/04/2022");
    }

    @Test void
    alert_fridge_door_is_opened() {
        fridge.signalFridgeDoorOpened();
        verify(smartFridgeService).openDoor();
    }

    @Test void
    alert_fridge_door_is_closed() {
        fridge.signalFridgeDoorClosed();
        verify(smartFridgeService).closeDoor();
    }

    @Test void
    add_item_to_fridge() {
        fridge.scanAddedItem("Milk", "10/04/2022", "sealed");
        verify(smartFridgeRepository).addItem("Milk", "10/04/2022", "sealed");
    }

    @Test void
    remove_item_from_fridge() {
        fridge.scanRemovedItem("Milk");
        verify(smartFridgeRepository).removeItem("Milk");
    }
}
