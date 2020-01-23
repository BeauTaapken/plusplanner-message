package plus.planner.messageservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
