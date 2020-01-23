package plus.planner.messageservice.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.planner.messageservice.Models.Message;
import plus.planner.messageservice.Repository.MessageRepository;

import java.io.IOException;
import java.util.List;

@RequestMapping("message")
@RestController
public class MessageController {
    private final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageRepository messageRepo;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageController(MessageRepository messageRepo, ObjectMapper objectMapper) {
        this.messageRepo = messageRepo;
        this.objectMapper = objectMapper;
    }

    @PostMapping(path = "/create")
    public void createMessage(@RequestBody String msg) throws IOException {
        final Message message = objectMapper.readValue(msg,Message.class);
        logger.info("saving message: " + message.getMessageid());
        messageRepo.save(message);
        logger.info("saved message");
    }

    @GetMapping(path = "/read/{channelid}")
    public List<Message> readMessage(@PathVariable String channelid) {
        logger.info("getting messages for channelid: " + channelid);
        final List<Message> messages = messageRepo.findByChannelId(channelid);
        logger.info("returning messages");
        return messages;
    }

    @PostMapping(path = "/update")
    public void updateMessage(@RequestBody Message message) {
        logger.info("updating message: " + message.getMessageid());
        messageRepo.save(message);
        logger.info("updated message");
    }

    @PostMapping(path = "/delete")
    public void deleteSubPart(@RequestBody String messageid) {
        logger.info("deleting message: " + messageid);
        messageRepo.deleteById(messageid);
        logger.info("deleted message");
    }
}
