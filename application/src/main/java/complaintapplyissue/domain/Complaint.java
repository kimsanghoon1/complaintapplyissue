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
    private Long 신청번호;

    private String 서식일렬번호;

    private String 서비스id;

    private String 서비스분류코드;

    private String 신청인명;

    private String 주민등록번호;

    private String 신청처리결과코드;

    private Date 신청일시;

    private Date 등록일시;

    private Date 수정일시;

    @Embedded
    private 구비서류 구비서류;

    @Embedded
    private 신청수령방법 신청수령방법;

    @Embedded
    private 신청서파일 신청서파일;

    @PostPersist
    public void onPostPersist() {
        ComplaintAccepted complaintAccepted = new ComplaintAccepted(this);
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
