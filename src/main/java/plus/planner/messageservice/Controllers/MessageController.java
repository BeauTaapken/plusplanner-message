package plus.planner.messageservice.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.planner.messageservice.Models.Message;
import plus.planner.messageservice.Repository.MessageRepository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("message")
@RestController
public class MessageController {
    @Autowired
    private MessageRepository messageRepo;
    private ObjectMapper mapper;
    private SimpleDateFormat formatter;

    MessageController() {
        mapper = new ObjectMapper();
        formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    }

    @RequestMapping(path = "/create/{message}")
    public void createMessage(@PathVariable String message) {
        try {
            Message m = mapper.readValue(message, Message.class);
            m.setSenddate((new Date(System.currentTimeMillis()).toString()));
            m.setUserid((long)0);
            messageRepo.save(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/read/{channelid}")
    public List<Message> readMessage(@PathVariable Long channelid) {
        List<Message> messages = messageRepo.findByChannelId(channelid);
        return messages;
    }

    @RequestMapping(path = "/update/{message}")
    public void updateMessage(@PathVariable String message) {
        try {
            messageRepo.save(mapper.readValue(message, Message.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/delete/{messageid}")
    public void deleteSubPart(@PathVariable Long messageid){
        messageRepo.deleteById(messageid);
    }
}
