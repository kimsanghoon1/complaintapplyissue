package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class EdmsStored extends AbstractEvent {

    private Long id;
    private String applicationNumber;
    private String fileId;
    private String fileName;
    private Boolean isStored;
    private String filePathName;
    private String fileExtension;
    private String fileSize;
    private String fileOrder;

    public EdmsStored(Edms aggregate) {
        super(aggregate);
    }

    public EdmsStored() {
        super();
    }
}
//>>> DDD / Domain Event
