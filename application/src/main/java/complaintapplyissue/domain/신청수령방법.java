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
public class 신청수령방법 {

    private String 수령방법코드;

    private String 수령방법명;

    private String 수령방법구분명;

    private String 등록자Id;

    private Date 등록일시;

    private String 수령자Id;

    private Date 수령일시;
}
//>>> DDD / Value Object
