package complaintapplyissue.domain;

import complaintapplyissue.AlarmApplication;
import complaintapplyissue.domain.ApplicationCompletionNotification;
import complaintapplyissue.domain.ProcessingCompletionNotification;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notification_table")
@Data
//<<< DDD / Aggregate Root
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String complaintId;

    private String complaintReceiptNumber;

    private String applicationNotificationSent;

    private String processingNotificationSent;

    @PostPersist
    public void onPostPersist() {
        ApplicationCompletionNotification applicationCompletionNotification = new ApplicationCompletionNotification(this);
        applicationCompletionNotification.publishAfterCommit();

        ProcessingCompletionNotification processingCompletionNotification = new ProcessingCompletionNotification(this);
        processingCompletionNotification.publishAfterCommit();
    }

    public static NotificationRepository repository() {
        NotificationRepository notificationRepository = AlarmApplication.applicationContext.getBean(
            NotificationRepository.class
        );
        return notificationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void sendNotification(EdmsStored edmsStored) {
        //implement business logic here:

        /** Example 1:  new item 
        Notification notification = new Notification();
        repository().save(notification);

        ApplicationCompletionNotification applicationCompletionNotification = new ApplicationCompletionNotification(notification);
        applicationCompletionNotification.publishAfterCommit();
        ProcessingCompletionNotification processingCompletionNotification = new ProcessingCompletionNotification(notification);
        processingCompletionNotification.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(edmsStored.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);

            ApplicationCompletionNotification applicationCompletionNotification = new ApplicationCompletionNotification(notification);
            applicationCompletionNotification.publishAfterCommit();
            ProcessingCompletionNotification processingCompletionNotification = new ProcessingCompletionNotification(notification);
            processingCompletionNotification.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void sendNotification(ComplaintAccepted complaintAccepted) {
        //implement business logic here:

        /** Example 1:  new item 
        Notification notification = new Notification();
        repository().save(notification);

        ApplicationCompletionNotification applicationCompletionNotification = new ApplicationCompletionNotification(notification);
        applicationCompletionNotification.publishAfterCommit();
        ProcessingCompletionNotification processingCompletionNotification = new ProcessingCompletionNotification(notification);
        processingCompletionNotification.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(complaintAccepted.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);

            ApplicationCompletionNotification applicationCompletionNotification = new ApplicationCompletionNotification(notification);
            applicationCompletionNotification.publishAfterCommit();
            ProcessingCompletionNotification processingCompletionNotification = new ProcessingCompletionNotification(notification);
            processingCompletionNotification.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
