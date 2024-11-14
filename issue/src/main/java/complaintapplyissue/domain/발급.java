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
@Table(name = "발급_table")
@Data
//<<< DDD / Aggregate Root
public class 발급 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long 발급파일일련번호;

    private String 신청번호;

    private String 파일Id;

    private String 파일명;

    private String 파일경로명;

    private String 파일확장자명;

    private String 파일크기;

    private String 파일유형코드;

    private String 파일순서;

    @Embedded
    private 파일유형코드 파일유형코드;

    @PostPersist
    public void onPostPersist() {
        Printed printed = new Printed(this);
        printed.publishAfterCommit();

        PrintRequested printRequested = new PrintRequested(this);
        printRequested.publishAfterCommit();
    }

    public static 발급Repository repository() {
        발급Repository 발급Repository = IssueApplication.applicationContext.getBean(
            발급Repository.class
        );
        return 발급Repository;
    }
}
//>>> DDD / Aggregate Root
