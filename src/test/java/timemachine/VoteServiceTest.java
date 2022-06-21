package timemachine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    VoteRepository voteRepository;

    TimeMachine timeMachine;

    VoteService voteService;

    LocalDateTime now;

    Vote vote;

    @BeforeEach
    void init() {
        timeMachine = new TimeMachine();
        voteService = new VoteService(voteRepository, timeMachine);
        now = LocalDateTime.parse("2022-06-01T12:00:00");
        vote = new Vote(now);
        when(voteRepository.findById(anyLong()))
                .thenReturn(vote);
    }

    @Test
    void testClose() {
       timeMachine.setNow(LocalDateTime.parse("2022-06-05T12:00:00"));
       voteService.closeVote(1L);

       assertEquals(LocalDateTime.parse("2022-06-05T12:00:00"), vote.getEndTime());
    }

    @Test
    void testCloseTooEarly() {
        timeMachine.setNow(now);

        assertThrows(IllegalArgumentException.class,
                () -> voteService.closeVote(1L));
    }
}