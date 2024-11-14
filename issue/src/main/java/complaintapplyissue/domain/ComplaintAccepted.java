package complaintapplyissue.domain;

import complaintapplyissue.infra.AbstractEvent;
import lombok.Data;
import java.util.*;
import java.time.LocalDate;


@Data
public class ComplaintAccepted extends AbstractEvent {

    private Long 신청데이터일렬번호;
    private String 신청번호;
    private String 서식JSON내용;
    private String 신청데이터XML내용;
    private String 신청메타XML내용;
    private Boolean 사용여부;
    private Date 등록일시;
    private Date 수정일시;
}
