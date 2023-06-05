import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    
    private void createMockRestaurant() {
        LocalTime openTime = LocalTime.parse("10:00:00");
        LocalTime closeTime = LocalTime.parse("23:00:00");
        restaurant = service.addRestaurant("Chat House", "Mumbai", openTime, closeTime);
        restaurant.addToMenu("Bhelpuri", 100);
        restaurant.addToMenu("Panipuri", 80);

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        createMockRestaurant();
        LocalTime testOpenTime = LocalTime.parse("09:30:00");
        Restaurant spiedMockedRestaurant = Mockito.spy(restaurant) ;
        Mockito.when(spiedMockedRestaurant.getCurrentTime()).thenReturn(testOpenTime) ;
        assertTrue(spiedMockedRestaurant.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        createMockRestaurant();
        LocalTime testOpenTime = LocalTime.parse("23:30:00");
        Restaurant spiedMockedRestaurant = Mockito.spy(restaurant) ;
        Mockito.when(spiedMockedRestaurant.getCurrentTime()).thenReturn(testOpenTime) ;
        assertFalse(spiedMockedRestaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        createMockRestaurant();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Chole Patis",200);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        createMockRestaurant();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Panipuri");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        createMockRestaurant();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("Idli Wada"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>GetCost<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	@Test
    public void select_item_from_list_should_return_order_cost(){
        // This will be the failing test case, as there is no functinality written yet for total cost.
            int totalCost;
            createMockRestaurant();
            List<String> selectedItemNames = Arrays.asList("Bhelpuri", "Panipuri" ) ;
            totalCost = restaurant.getTotalCost(selectedItemNames) ;
            assertEquals(180, totalCost);
    }
}