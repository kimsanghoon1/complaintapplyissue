package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class EdmsStored extends AbstractEvent {

    private Long id;
    private String 신청번호;
    private String 파일id;
    private String 파일명;
    private Boolean 저장유무;
}
