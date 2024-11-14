package complaintapplyissue.domain;

import complaintapplyissue.IssueApplication;
import complaintapplyissue.domain.PrintRequested;
import complaintapplyissue.domain.Printed;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Issurance_table")
@Data
//<<< DDD / Aggregate Root
public class Issurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long 발급파일일련번호;

    private String 신청번호;

    private String 파일Id;

    private String 파일명;

    private String 파일경로명;

    private String 파일확장자명;

    private String 파일크기;

    private String 파일순서;

    @Embedded
    private FileType fileType;

    private String status;

    @PostPersist
    public void onPostPersist() {
        Printed printed = new Printed(this);
        printed.publishAfterCommit();

        PrintRequested printRequested = new PrintRequested(this);
        printRequested.publishAfterCommit();
    }

    public static IssuranceRepository repository() {
        IssuranceRepository issuranceRepository = IssueApplication.applicationContext.getBean(
            IssuranceRepository.class
        );
        return issuranceRepository;
    }

    //<<< Clean Arch / Port Method
    public static void 처리정보수신(EdmsStored edmsStored) {
        //implement business logic here:

        /** Example 1:  new item 
        Issurance issurance = new Issurance();
        repository().save(issurance);

        PrintRequested printRequested = new PrintRequested(issurance);
        printRequested.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(edmsStored.get???()).ifPresent(issurance->{
            
            issurance // do something
            repository().save(issurance);

            PrintRequested printRequested = new PrintRequested(issurance);
            printRequested.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void 신청등록(ComplaintAccepted complaintAccepted) {
        //implement business logic here:

        /** Example 1:  new item 
        Issurance issurance = new Issurance();
        repository().save(issurance);

        */

        /** Example 2:  finding and process
        
        repository().findById(complaintAccepted.get???()).ifPresent(issurance->{
            
            issurance // do something
            repository().save(issurance);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
