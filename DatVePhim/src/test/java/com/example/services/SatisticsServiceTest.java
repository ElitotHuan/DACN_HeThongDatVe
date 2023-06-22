package com.example.services;
import com.example.dto.SatisticDTO;
import com.example.repositories.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SatisticsServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private SatisticsService satisticsService;

    @Test
    void testGetTotalFromAllBranch() {
        List<SatisticDTO> statistics = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            SatisticDTO statistic = new SatisticDTO() {
                @Override
                public String getName() {
                    return "Branch" + (finalI + 1);
                }

                @Override
                public Double getTotal() {
                    return 100.0 * (finalI + 1);
                }
            };
            statistics.add(statistic);
        }

        when(ticketRepository.getTotalOfBranch()).thenReturn(statistics);

        List<SatisticDTO> result = satisticsService.getTotalFromAllBranch();
        assertEquals(statistics.size(), result.size());
    }
}