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
    private String complaintId;
    private String complaintReceiptNumber;
    private String issuanceStatus;
    private String viewerExecutionStatus;
    private String certificateStatus;
    private String printStatus;

    public Printed(Issurance aggregate) {
        super(aggregate);
    }

    public Printed() {
        super();
    }
}
//>>> DDD / Domain Event
