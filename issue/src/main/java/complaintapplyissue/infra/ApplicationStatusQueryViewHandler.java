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

            // view 객체 생성
            ApplicationStatusQuery applicationStatusQuery = new ApplicationStatusQuery();
            // view 객체에 이벤트의 Value 를 set 함
            applicationStatusQuery.setStatus("신청완료");
            applicationStatusQuery.setId(complaintAccepted.get신청번호());
            // view 레파지 토리에 save
            applicationStatusQueryRepository.save(applicationStatusQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenEdmsStored_then_UPDATE_1(@Payload EdmsStored edmsStored) {
        try {
            if (!edmsStored.validate()) return;
            // view 객체 조회
            Optional<ApplicationStatusQuery> applicationStatusQueryOptional = applicationStatusQueryRepository.findById(
                Long.valueOf(edmsStored.get신청번호())
            );

            if (applicationStatusQueryOptional.isPresent()) {
                ApplicationStatusQuery applicationStatusQuery = applicationStatusQueryOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                applicationStatusQuery.setStatus("발급완료");
                applicationStatusQuery.setEdmsDocumentId(
                    edmsStored.get파일id()
                );
                // view 레파지 토리에 save
                applicationStatusQueryRepository.save(applicationStatusQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
