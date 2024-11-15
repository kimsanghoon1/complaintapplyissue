package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ComplaintAccepted extends AbstractEvent {

    private Long applicationDataSerialNumber;
    private String applicationNumber;
    private String formJsonContent;
    private String applicationDataXmlContent;
    private String applicationMetaXmlContent;
    private Boolean isActive;
    private Date registrationDate;
    private Date modificationDate;

    public ComplaintAccepted(Complaint aggregate) {
        super(aggregate);
    }

    public ComplaintAccepted() {
        super();
    }
}
//>>> DDD / Domain Event
