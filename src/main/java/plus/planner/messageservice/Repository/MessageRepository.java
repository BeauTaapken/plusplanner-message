package plus.planner.messageservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plus.planner.messageservice.Models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
