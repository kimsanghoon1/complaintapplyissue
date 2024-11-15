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
public class ReceiveMethod {

    private String receiveMethodCode;

    private String receiveMethodName;

    private String receiveMethodTypeName;

    private String registrantId;

    private Date registrationDateTime;

    private String recipientId;

    private Date receiptDateTime;
}
//>>> DDD / Value Object
