package complaintapplyissue.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import complaintapplyissue.config.kafka.KafkaProcessor;
import complaintapplyissue.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    NotificationRepository notificationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='EdmsStored'"
    )
    public void wheneverEdmsStored_sendNotification(@Payload EdmsStored edmsStored) {
        EdmsStored event = edmsStored;
        System.out.println(
            "\n\n##### listener sendNotification : " + edmsStored + "\n\n"
        );

        // Sample Logic //
        Notification.sendNotification(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ComplaintAccepted'"
    )
    public void wheneverComplaintAccepted_sendNotification(
        @Payload ComplaintAccepted complaintAccepted
    ) {
        ComplaintAccepted event = complaintAccepted;
        System.out.println(
            "\n\n##### listener sendNotification : " + complaintAccepted + "\n\n"
        );

        // Sample Logic //
        Notification.sendNotification(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
