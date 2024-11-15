package complaintapplyissue.domain;

import complaintapplyissue.ApplicationApplication;
import complaintapplyissue.domain.ComplaintAccepted;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Complaint_table")
@Data
//<<< DDD / Aggregate Root
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationNumber;

    private String formSerialNumber;

    private String serviceId;

    private String serviceCategoryCode;

    private String applicantName;

    private String residentRegistrationNumber;

    private String applicationProcessingResultCode;

    private Date applicationDateTime;

    private Date registrationDateTime;

    private Date modificationDateTime;

    @Embedded
    private ApplicationFile requiredDocuments;

    @Embedded
    private ReceiveMethod receiveMethod;

    @Embedded
    private ApplicationFile applicationFile;

    @PostPersist
    public void onPostPersist() {
        ComplaintAccepted complaintAccepted = new ComplaintAccepted(this);
        complaintAccepted.setApplicationNumber(getApplicationNumber().toString());
        complaintAccepted.publishAfterCommit();
    }

    public static ComplaintRepository repository() {
        ComplaintRepository complaintRepository = ApplicationApplication.applicationContext.getBean(
            ComplaintRepository.class
        );
        return complaintRepository;
    }
}
//>>> DDD / Aggregate Root
