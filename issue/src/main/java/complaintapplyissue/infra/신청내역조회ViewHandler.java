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
public class 신청내역조회ViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private 신청내역조회Repository 신청내역조회Repository;
    //>>> DDD / CQRS
}
