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
    IntegrationRepository integrationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ComplaintAccepted'"
    )
    public void wheneverComplaintAccepted_registerIntegrationTask(
        @Payload ComplaintAccepted complaintAccepted
    ) {
        ComplaintAccepted event = complaintAccepted;
        System.out.println(
            "\n\n##### listener registerIntegrationTask : " + complaintAccepted + "\n\n"
        );

        // Sample Logic //
        Integration.registerIntegrationTask(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IntegrationReqistered'"
    )
    public void wheneverIntegrationReqistered_callResponsibleDepartmentService(
        @Payload IntegrationRegistered integrationRegistered
    ) {
        IntegrationRegistered event = integrationRegistered;
        System.out.println(
            "\n\n##### listener callResponsibleDepartmentService : " +
            integrationRegistered +
            "\n\n"
        );

        // Sample Logic //
        Integration.callResponsibleDepartmentService(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
