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
    IssuranceRepository issuranceRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='EdmsStored'"
    )
    public void wheneverEdmsStored_receiveProcessingInfo(
        @Payload EdmsStored edmsStored
    ) {
        EdmsStored event = edmsStored;
        System.out.println(
            "\n\n##### listener receiveProcessingInfo : " + edmsStored + "\n\n"
        );

        // Sample Logic //
        Issurance.receiveProcessingInfo(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ComplaintAccepted'"
    )
    public void wheneverComplaintAccepted_registerApplication(
        @Payload ComplaintAccepted complaintAccepted
    ) {
        ComplaintAccepted event = complaintAccepted;
        System.out.println(
            "\n\n##### listener registerApplication : " + complaintAccepted + "\n\n"
        );

        // Sample Logic //
        Issurance.registerApplication(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
