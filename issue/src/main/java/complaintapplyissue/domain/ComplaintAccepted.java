package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.util.*;
import lombok.*;

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
}
