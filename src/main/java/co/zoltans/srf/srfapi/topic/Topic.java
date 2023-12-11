package co.zoltans.srf.srfapi.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "Topic",
        uniqueConstraints = {
                @UniqueConstraint(name = "topic_id_uuid_unique", columnNames = "id")
        }
)
@Entity(name = "Topic")
public class Topic {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(
            name = "topic_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String topicName;
}