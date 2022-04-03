package kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SmartFridgeShould {
    private SmartFridge fridge;
    private SmartFridgeService smartFridgeService;
    private SmartFridgeRepository smartFridgeRepository;
    private ConsolePrinter printer;
    private ItemsPrinter itemsPrinter;

    @BeforeEach
    void setUp() {
        smartFridgeService = mock(SmartFridgeService.class);
        smartFridgeRepository = mock(SmartFridgeRepository.class);
        fridge = new SmartFridge(smartFridgeService, smartFridgeRepository);
        printer = mock(ConsolePrinter.class);
        itemsPrinter = mock(ItemsPrinter.class);
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

    @Test void
    end_current_day() {
        fridge.simulateDayOver();
        verify(smartFridgeService).startNewDay(fridge.currentDate);
    }

    @Test void
    print_fridge_items() {
        List<Item> items = new ArrayList<>();

        when(SmartFridgeRepository.getItems()).thenReturn(items);
        fridge.showDisplay();

        verify(itemsPrinter).print(items);
    }
}
