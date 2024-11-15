package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ProcessingCompletionNotification extends AbstractEvent {

    private Long id;
    private String complaintId;
    private String complaintReceiptNumber;
    private String applicationNotificationSent;
    private String processingNotificationSent;

    public ProcessingCompletionNotification(Notification aggregate) {
        super(aggregate);
    }

    public ProcessingCompletionNotification() {
        super();
    }
}
//>>> DDD / Domain Event
