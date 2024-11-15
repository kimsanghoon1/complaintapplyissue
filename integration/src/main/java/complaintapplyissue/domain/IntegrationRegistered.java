package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class IntegrationRegistered extends AbstractEvent {

    private Long integrationId;

    public IntegrationRegistered() {
        super();
    }

    public IntegrationRegistered(Integration aggregate) {
        super(aggregate);
    }
}
//>>> DDD / Domain Event
