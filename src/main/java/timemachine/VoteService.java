package timemachine;

import lombok.AllArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
public class VoteService {

    private VoteRepository voteRepository;

    private TimeMachine timeMachine;

    public void closeVote(Long id) {
        Vote vote = voteRepository.findById(id);
        LocalDateTime now = timeMachine.getNow();

        if (Duration.between(vote.getStartTime(), now).toDays() < 3) {
            throw new IllegalArgumentException("Túl korán akarod a szavazást lezárni!");
        }
        vote.setEndTime(now);
    }
}
