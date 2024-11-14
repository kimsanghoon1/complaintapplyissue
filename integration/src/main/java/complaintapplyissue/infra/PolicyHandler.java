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
    public void wheneverComplaintAccepted_연계작업등록(
        @Payload ComplaintAccepted complaintAccepted
    ) {
        ComplaintAccepted event = complaintAccepted;
        System.out.println(
            "\n\n##### listener 연계작업등록 : " + complaintAccepted + "\n\n"
        );

        // Sample Logic //
        Integration.연계작업등록(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='IntegrationReqistered'"
    )
    public void wheneverIntegrationReqistered_소관부처서비스호출(
        @Payload IntegrationReqistered integrationReqistered
    ) {
        IntegrationReqistered event = integrationReqistered;
        System.out.println(
            "\n\n##### listener 소관부처서비스호출 : " +
            integrationReqistered +
            "\n\n"
        );

        // Sample Logic //
        Integration.소관부처서비스호출(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
