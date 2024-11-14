package complaintapplyissue.domain;

import complaintapplyissue.IntegrationApplication;
import complaintapplyissue.domain.RelationPartyServiceDone;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Integration_table")
@Data
//<<< DDD / Aggregate Root
public class Integration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long 연계Id;

    private String 신청번호;

    private String 서비스Id;

    @Embedded
    private TypeSequenceNo 유형일렬번호;

    private String 연계인터페이스;

    private String 송신데이터;

    private String 수신데이터;

    private String 결과코드;

    private String 결과메시지;

    private Date 송신일시;

    private Date 수신일시;

    private Date 등록일시;

    private Date 수정일시;

    @PostPersist
    public void onPostPersist() {
        RelationPartyServiceDone relationPartyServiceDone = new RelationPartyServiceDone(
            this
        );
        relationPartyServiceDone.publishAfterCommit();
    }

    public static IntegrationRepository repository() {
        IntegrationRepository integrationRepository = IntegrationApplication.applicationContext.getBean(
            IntegrationRepository.class
        );
        return integrationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void 소관부서호출(ComplaintAccepted complaintAccepted) {
        //implement business logic here:

        /** Example 1:  new item 
        Integration integration = new Integration();
        repository().save(integration);

        */

        /** Example 2:  finding and process
        
        repository().findById(complaintAccepted.get???()).ifPresent(integration->{
            
            integration // do something
            repository().save(integration);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
