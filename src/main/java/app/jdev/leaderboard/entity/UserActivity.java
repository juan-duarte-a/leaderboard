package app.jdev.leaderboard.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
public class UserActivity {

    private String userId;
    private String activityType;
    private LocalDateTime timestamp;

    public UserActivity() {
    }

    public UserActivity(String userId, String activityType, LocalDateTime timestamp) {
        this.userId = userId;
        this.activityType = activityType;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        UserActivity that = (UserActivity) object;

        if (!Objects.equals(userId, that.userId)) return false;
        if (!Objects.equals(activityType, that.activityType)) return false;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (activityType != null ? activityType.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

}
