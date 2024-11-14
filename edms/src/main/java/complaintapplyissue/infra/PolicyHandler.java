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
    EdmsRepository edmsRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='RelationPartyServiceDone'"
    )
    public void wheneverRelationPartyServiceDone_문서저장(
        @Payload RelationPartyServiceDone relationPartyServiceDone
    ) {
        RelationPartyServiceDone event = relationPartyServiceDone;
        System.out.println(
            "\n\n##### listener 문서저장 : " + relationPartyServiceDone + "\n\n"
        );

        // Sample Logic //
        Edms.문서저장(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
