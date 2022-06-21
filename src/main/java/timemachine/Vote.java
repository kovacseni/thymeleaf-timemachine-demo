package timemachine;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Vote {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public Vote(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
