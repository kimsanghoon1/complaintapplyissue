package complaintapplyissue.domain;

import complaintapplyissue.infra.AbstractEvent;
import lombok.Data;
import java.util.*;
import java.time.LocalDate;


@Data
public class ComplaintAccepted extends AbstractEvent {

    private Long 신청데이터일렬번호;
    private String 신청 번호;
    private String 서식 JSON 내용;
    private String 신청데이터 XML 내용;
    private String 신청 메타 XML내용;
    private Boolean 사용 여부;
    private Date 등록 일시;
    private Date 수정 일시;
}
