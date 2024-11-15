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
    private Long issuanceFileSerialNumber;

    private String applicationNumber;

    private String fileId;

    private String fileName;

    private String filePathName;

    private String fileExtensionName;

    private String fileSize;

    private String fileOrder;

    private String status;

    @Embedded
    private ComplaintApplicationId complaintApplicationId;

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
    public static void receiveProcessingInfo(EdmsStored edmsStored) {

        repository().findByApplicationNumber(edmsStored.getApplicationNumber()).ifPresent(issurance -> {
            
            issurance.setStatus("Issued"); 
            issurance.setFileId(edmsStored.getId().toString());
            issurance.setFilePathName(edmsStored.getFilePathName());
            issurance.setFileName(edmsStored.getFileName());
            issurance.setFileSize(edmsStored.getFileSize());
            issurance.setFileExtensionName(edmsStored.getFileExtension());
            
            repository().save(issurance);

            PrintRequested printRequested = new PrintRequested(issurance);
            printRequested.publishAfterCommit();

         });
       

    }

   
    public static void registerApplication(ComplaintAccepted complaintAccepted) {

        Issurance issurance = new Issurance();
        issurance.setApplicationNumber(complaintAccepted.getApplicationNumber());
        issurance.setStatus("Applying");
        repository().save(issurance);

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
