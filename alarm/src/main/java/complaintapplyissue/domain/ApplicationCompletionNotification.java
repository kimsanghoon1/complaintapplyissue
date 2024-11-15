package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import complaintapplyissue.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ApplicationCompletionNotification extends AbstractEvent {

    private Long id;
    private String complaintId;
    private String complaintReceiptNumber;
    private String applicationNotificationSent;
    private String processingNotificationSent;

    public ApplicationCompletionNotification(Notification aggregate) {
        super(aggregate);
    }

    public ApplicationCompletionNotification() {
        super();
    }
}
//>>> DDD / Domain Event
