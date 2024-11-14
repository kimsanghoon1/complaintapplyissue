package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class EdmsStored extends AbstractEvent {

    private Long id;
    private String 신청번호;
    private String 파일id;
    private String 파일명;
    private Boolean 저장유무;
    private String 파일경로명;
    private String 파일확장자명;
    private String 파일크기;
    private String 파일순서;


    public EdmsStored(Edms aggregate) {
        super(aggregate);
    }

    public EdmsStored() {
        super();
    }
}
//>>> DDD / Domain Event
