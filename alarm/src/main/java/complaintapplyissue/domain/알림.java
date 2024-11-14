package complaintapplyissue.domain;

import complaintapplyissue.AlarmApplication;
import complaintapplyissue.domain.신청완료알림;
import complaintapplyissue.domain.처리완료알림;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "알림_table")
@Data
//<<< DDD / Aggregate Root
public class 알림 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String 민원id;

    private String 민원접수번호;

    private String 신청알림발송여부;

    private String 처리알림발송여부;

    @PostPersist
    public void onPostPersist() {
        신청완료알림 신청완료알림 = new 신청완료알림(this);
        신청완료알림.publishAfterCommit();

        처리완료알림 처리완료알림 = new 처리완료알림(this);
        처리완료알림.publishAfterCommit();
    }

    public static 알림Repository repository() {
        알림Repository 알림Repository = AlarmApplication.applicationContext.getBean(
            알림Repository.class
        );
        return 알림Repository;
    }

    //<<< Clean Arch / Port Method
    public static void 알림발송(EdmsStored edmsStored) {
        //implement business logic here:

        /** Example 1:  new item 
        알림 알림 = new 알림();
        repository().save(알림);

        신청완료알림 신청완료알림 = new 신청완료알림(알림);
        신청완료알림.publishAfterCommit();
        처리완료알림 처리완료알림 = new 처리완료알림(알림);
        처리완료알림.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(edmsStored.get???()).ifPresent(알림->{
            
            알림 // do something
            repository().save(알림);

            신청완료알림 신청완료알림 = new 신청완료알림(알림);
            신청완료알림.publishAfterCommit();
            처리완료알림 처리완료알림 = new 처리완료알림(알림);
            처리완료알림.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void 알림발송(ComplaintAccepted complaintAccepted) {
        //implement business logic here:

        /** Example 1:  new item 
        알림 알림 = new 알림();
        repository().save(알림);

        신청완료알림 신청완료알림 = new 신청완료알림(알림);
        신청완료알림.publishAfterCommit();
        처리완료알림 처리완료알림 = new 처리완료알림(알림);
        처리완료알림.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(complaintAccepted.get???()).ifPresent(알림->{
            
            알림 // do something
            repository().save(알림);

            신청완료알림 신청완료알림 = new 신청완료알림(알림);
            신청완료알림.publishAfterCommit();
            처리완료알림 처리완료알림 = new 처리완료알림(알림);
            처리완료알림.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
