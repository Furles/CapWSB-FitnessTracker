package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;

// TODO: Define the Event entity with appropriate fields and annotations
@Entity
@Table(name = "workout_session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(name = "start_latitude", nullable = false)
    private double startLatitude;
    @Column(name = "start_longitude", nullable = false)
    private double startLongitude;
    @Column(name = "end_latitude", nullable = false)
    private double endLatitude;
    @Column(name = "end_longitude", nullable = false)
    private double endLongitude;
    @Column(nullable = false)
    private double altitude;

    public WorkoutSession(
            Training training,
            Date timestamp,
            double startLatitude,
            double startLongitude,
            double endLatitude,
            double endLongitude,
            double altitude
    ) {
        this.training = training;
        this.timestamp = timestamp;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        this.altitude = altitude;
    }
}

