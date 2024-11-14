package complaintapplyissue.domain;

import complaintapplyissue.EdmsApplication;
import complaintapplyissue.domain.EdmsStored;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Edms_table")
@Data
//<<< DDD / Aggregate Root
public class Edms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String 신청번호;

    private String 파일id;

    private String 파일명;

    private Boolean 저장유무;

    @PostPersist
    public void onPostPersist() {
        EdmsStored edmsStored = new EdmsStored(this);
        edmsStored.publishAfterCommit();
    }

    public static EdmsRepository repository() {
        EdmsRepository edmsRepository = EdmsApplication.applicationContext.getBean(
            EdmsRepository.class
        );
        return edmsRepository;
    }

    //<<< Clean Arch / Port Method
    public static void 문서저장(
        RelationPartyServiceDone relationPartyServiceDone
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Edms edms = new Edms();
        repository().save(edms);

        */

        /** Example 2:  finding and process
        
        repository().findById(relationPartyServiceDone.get???()).ifPresent(edms->{
            
            edms // do something
            repository().save(edms);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
