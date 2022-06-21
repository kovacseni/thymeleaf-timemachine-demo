package timemachine;

import java.time.LocalDateTime;

public class TimeMachine {

    private LocalDateTime timemachineNow;

    public void setNow(LocalDateTime now) {
        this.timemachineNow = now;
    }

    public LocalDateTime getNow() {
        if (timemachineNow == null) {
            return LocalDateTime.now();
        }
        else {
            return timemachineNow;
        }
    }
}
