package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.util.*;
import lombok.*;

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
}
