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

    private String status;

    @Embedded
    private ComplaintApplicationId 민원접수id;

    @Embedded
    private FileType fileType;

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

        repository().findBy신청번호(edmsStored.get신청번호()).ifPresent(issurance->{
            
            issurance.setStatus("발급완료"); 
            issurance.set파일Id(edmsStored.getId().toString());
            issurance.set파일경로명(edmsStored.get파일경로명());
            issurance.set파일명(edmsStored.get파일명());
            issurance.set파일크기(edmsStored.get파일크기());
            issurance.set파일확장자명(edmsStored.get파일확장자명());
            
            repository().save(issurance);

            PrintRequested printRequested = new PrintRequested(issurance);
            printRequested.publishAfterCommit();

         });
       

    }

   
    public static void 신청등록(ComplaintAccepted complaintAccepted) {

        Issurance issurance = new Issurance();
        issurance.set신청번호(complaintAccepted.get신청번호());
        issurance.setStatus("신청중");
        repository().save(issurance);

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
