package plus.planner.messageservice.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.planner.messageservice.Models.Message;
import plus.planner.messageservice.Repository.MessageRepository;

import java.util.List;

@RequestMapping("message")
@RestController
public class MessageController {
    private final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageRepository messageRepo;

    @Autowired
    public MessageController(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public void createMessage(@RequestBody Message message) {
        logger.info("saving message: " + message.getMessageid());
        messageRepo.save(message);
        logger.info("saved message");
    }

    @RequestMapping(path = "/read/{channelid}", method = RequestMethod.GET)
    public List<Message> readMessage(@PathVariable String channelid) {
        logger.info("getting messages for channelid: " + channelid);
        final List<Message> messages = messageRepo.findByChannelId(channelid);
        logger.info("returning messages");
        return messages;
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public void updateMessage(@RequestBody Message message) {
        logger.info("updating message: " + message.getMessageid());
        messageRepo.save(message);
        logger.info("updated message");
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void deleteSubPart(@RequestBody String messageid) {
        logger.info("deleting message: " + messageid);
        messageRepo.deleteById(messageid);
        logger.info("deleted message");
    }
}
