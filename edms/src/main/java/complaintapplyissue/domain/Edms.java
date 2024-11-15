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

    private String applicationNumber;

    private String fileId;

    private String fileName;

    private Boolean isStored;

    private String filePath;

    private String fileExtension;

    private String fileSize;

    private String fileOrder;

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
    public static void saveDocument(
        RelationPartyServiceDone relationPartyServiceDone
    ) {

        Edms edms = new Edms();
        edms.setApplicationNumber(relationPartyServiceDone.getApplicationNumber());
        edms.setFileName(relationPartyServiceDone.getReceptionData());
        edms.setIsStored(true);
        edms.setFilePath("/documents/" + edms.getFileName());
        edms.setFileSize(Math.random()*100 + " mb");
        edms.setFileExtension("pdf");
        

        repository().save(edms);

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
