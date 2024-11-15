package complaintapplyissue.domain;

import complaintapplyissue.IntegrationApplication;
import complaintapplyissue.domain.IntegrationRegistered;
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
    private Long integrationId;

    private String applicationNumber;

    private String serviceId;

    @Embedded
    private TypeSequenceNo typeSequenceNumber;

    private String integrationInterface;

    private String sentData;

    private String receivedData;

    private String resultCode;

    private String resultMessage;

    private Date sentTimestamp;

    private Date receivedTimestamp;

    private Date createdTimestamp;

    private Date updatedTimestamp;

    @PostPersist
    public void onPostPersist() {
        IntegrationRegistered integrationRegistered = new IntegrationRegistered(this);
        integrationRegistered.publishAfterCommit();
    }

    public static IntegrationRepository repository() {
        IntegrationRepository integrationRepository = IntegrationApplication.applicationContext.getBean(
            IntegrationRepository.class
        );
        return integrationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void registerIntegrationTask(ComplaintAccepted complaintAccepted) {
        //implement business logic here:

    
        /** Example 1:  new item*/ 
        Integration integration = new Integration();
        integration.setApplicationNumber(complaintAccepted.getApplicationNumber());
        integration.setResultCode("In Progress");
        repository().save(integration);

        IntegrationRegistered integrationReqistered = new IntegrationRegistered(integration);
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
    public static void callResponsibleDepartmentService(
        IntegrationRegistered integrationRegistered
    ) {
       
        //실제 시스템 연동시 60 프로 확률로 성공함
        if(Math.random() > 0.6){
            repository().findById(integrationRegistered.getIntegrationId()).ifPresent(integration->{
            
                integration.setResultCode("Completed");
                integration.setSentData("Complaint Sent Details-"+ integrationRegistered.getIntegrationId());
                integration.setReceivedData("Complaint Handling Details-"+ integrationRegistered.getIntegrationId());
                repository().save(integration);
    
                RelationPartyServiceDone relationPartyServiceDone = new RelationPartyServiceDone(integration);
                relationPartyServiceDone.publishAfterCommit();
    
             });
           
        }else
            throw new RuntimeException("Error Occurred");

         
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
