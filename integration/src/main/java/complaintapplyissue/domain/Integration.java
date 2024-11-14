package complaintapplyissue.domain;

import complaintapplyissue.IntegrationApplication;
import complaintapplyissue.domain.IntegrationReqistered;
import complaintapplyissue.domain.RelationPartyServiceDone;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
        IntegrationReqistered integrationReqistered = new IntegrationReqistered(
            this
        );
        integrationReqistered.publishAfterCommit();
    }

    public static IntegrationRepository repository() {
        IntegrationRepository integrationRepository = IntegrationApplication.applicationContext.getBean(
            IntegrationRepository.class
        );
        return integrationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void 연계작업등록(ComplaintAccepted complaintAccepted) {
        //implement business logic here:

    
        /** Example 1:  new item*/ 
        Integration integration = new Integration();
        integration.set신청번호(complaintAccepted.get신청번호());
        integration.set결과코드("연계중");
        repository().save(integration);

        IntegrationReqistered integrationReqistered = new IntegrationReqistered(integration);
        integrationReqistered.publishAfterCommit();
        

        /** Example 2:  finding and process
        
        repository().findById(complaintAccepted.get???()).ifPresent(integration->{
            
            integration // do something
            repository().save(integration);

            IntegrationReqistered integrationReqistered = new IntegrationReqistered(integration);
            integrationReqistered.publishAfterCommit();

         });
        */
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void 소관부처서비스호출(
        IntegrationReqistered integrationReqistered
    ) {
       
        //실제 시스템 연동시 60 프로 확률로 성공함
        if(Math.random() > 0.6){
            repository().findById(integrationReqistered.get연계Id()).ifPresent(integration->{
            
                integration.set결과코드("연계완료됨");
                integration.set송신데이터("민원송신내역-"+ integrationReqistered.get연계Id());
                integration.set수신데이터("민원치리내역-"+ integrationReqistered.get연계Id());
                repository().save(integration);
    
                RelationPartyServiceDone relationPartyServiceDone = new RelationPartyServiceDone(integration);
                relationPartyServiceDone.publishAfterCommit();
    
             });
           
        }else
            throw new RuntimeException("장애발생");

         
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
