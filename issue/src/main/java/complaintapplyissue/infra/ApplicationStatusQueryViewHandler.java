package complaintapplyissue.infra;

import complaintapplyissue.config.kafka.KafkaProcessor;
import complaintapplyissue.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ApplicationStatusQueryViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private ApplicationStatusQueryRepository applicationStatusQueryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenComplaintAccepted_then_CREATE_1(
        @Payload ComplaintAccepted complaintAccepted
    ) {
        try {
            if (!complaintAccepted.validate()) return;

            // Create view object
            ApplicationStatusQuery applicationStatusQuery = new ApplicationStatusQuery();
            // Set the event's value to the view object
            applicationStatusQuery.setStatus("Application Completed");
            applicationStatusQuery.setId(complaintAccepted.getApplicationNumber());
            // Save to the view repository
            applicationStatusQueryRepository.save(applicationStatusQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenEdmsStored_then_UPDATE_1(@Payload EdmsStored edmsStored) {
        try {
            if (!edmsStored.validate()) return;
            // Retrieve view object
            Optional<ApplicationStatusQuery> applicationStatusQueryOptional = applicationStatusQueryRepository.findById(
                Long.valueOf(edmsStored.getApplicationNumber())
            );

            if (applicationStatusQueryOptional.isPresent()) {
                ApplicationStatusQuery applicationStatusQuery = applicationStatusQueryOptional.get();
                // Set the event's direct value to the view object
                applicationStatusQuery.setStatus("Issuance Completed");
                applicationStatusQuery.setEdmsDocumentId(
                    edmsStored.getFileId()
                );
                // Save to the view repository
                applicationStatusQueryRepository.save(applicationStatusQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
