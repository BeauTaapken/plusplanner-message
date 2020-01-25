package plus.planner.messageservice.models;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
@EntityListeners(AuditingEntityListener.class)
public class Message {
    @Id
    private String messageid;
    private String channelid;
    private String userid;
    private String content;
    private String senddate;
}
