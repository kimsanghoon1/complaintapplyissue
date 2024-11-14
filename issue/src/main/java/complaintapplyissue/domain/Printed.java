package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class Printed extends AbstractEvent {

    private Long id;
    private String 민원id;
    private String 민원접수번호;
    private String 발급물여부;
    private String 뷰어실행여부;
    private String 증명서여부;
    private String 출력여부;

    public Printed(발급 aggregate) {
        super(aggregate);
    }

    public Printed() {
        super();
    }
}
//>>> DDD / Domain Event
