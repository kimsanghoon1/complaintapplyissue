package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class RelationPartyServiceDone extends AbstractEvent {

    private Long 연계Id;
    private String 신청번호;
    private String 서비스Id;
    private Object 유형일렬번호;
    private String 연계인터페이스;
    private String 송신데이터;
    private String 수신데이터;
    private String 결과코드;
    private String 결과메시지;
    private Date 송신일시;
    private Date 수신일시;
    private Date 등록일시;
    private Date 수정일시;
}
