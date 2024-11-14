package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class IntegrationReqistered extends AbstractEvent {

    private Long 연계Id;

    public IntegrationReqistered(Integration aggregate) {
        super(aggregate);
    }

    public IntegrationReqistered() {
        super();
    }
}
//>>> DDD / Domain Event
