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

    private String 파일경로명;

    private String 파일확장자명;

    private String 파일크기;

    private String 파일순서;

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

        Edms edms = new Edms();
        edms.set신청번호(relationPartyServiceDone.get신청번호());
        edms.set파일명(relationPartyServiceDone.get수신데이터());
        edms.set저장유무(true);
        edms.set파일경로명("/documents/" + edms.get파일명());
        edms.set파일크기(Math.random()*100 + " mb");
        edms.set파일확장자명("pdf");
        

        repository().save(edms);

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
