package com.example;

import com.example.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class DatVePhimApplicationTests {

    @MockBean
    private TicketServiceTest ticketService;

    @MockBean
    private BranchServiceTest branchService;

    @MockBean
    private CartServiceTest cartService;

    @MockBean
    private FoodServiceTest foodService;

    @MockBean
    private MovieServiceTest movieService;

    @MockBean
    private RoleServiceTest roleService;

    @MockBean
    private RoomServiceTest roomService;

    @MockBean
    private SatisticsServiceTest satisticsService;

    @MockBean
    private ScheduleServiceTest scheduleService;

    @MockBean UserServiceTest userService;
    @Test
    void contextLoads() {
        assertThat(ticketService).isNotNull();
        assertThat(branchService).isNotNull();
        assertThat(cartService).isNotNull();
        assertThat(foodService).isNotNull();
        assertThat(movieService).isNotNull();
        assertThat(roleService).isNotNull();
        assertThat(roomService).isNotNull();
        assertThat(satisticsService).isNotNull();
        assertThat(scheduleService).isNotNull();
        assertThat(userService).isNotNull();
    }

}
