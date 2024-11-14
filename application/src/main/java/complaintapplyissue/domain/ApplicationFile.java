package complaintapplyissue.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

//<<< DDD / Value Object
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationFile {

    private String 파일Id;

    private String 파일명;

    private String 파일확장자명;

    private 파일유형코드 파일유형코드;

    private String 파일순서;

    private Boolean 사용여부;

    private Date 등록일시;

    private Date 수정일시;
}
//>>> DDD / Value Object
