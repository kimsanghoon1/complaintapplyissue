package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class 신청완료알림 extends AbstractEvent {

    private Long id;
    private String 민원id;
    private String 민원접수번호;
    private String 신청알림발송여부;
    private String 처리알림발송여부;

    public 신청완료알림(알림 aggregate) {
        super(aggregate);
    }

    public 신청완료알림() {
        super();
    }
}
//>>> DDD / Domain Event
