package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class RelationPartyServiceDone extends AbstractEvent {

    private Long linkageId;
    private String applicationNumber;
    private String serviceId;
    private Object typeSerialNumber;
    private String linkageInterface;
    private String transmissionData;
    private String receptionData;
    private String resultCode;
    private String resultMessage;
    private Date transmissionDateTime;
    private Date receptionDateTime;
    private Date registrationDateTime;
    private Date modificationDateTime;
}
