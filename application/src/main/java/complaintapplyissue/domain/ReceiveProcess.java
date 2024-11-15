package complaintapplyissue.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//<<< DDD / Value Object
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveProcess {

    private String receiveMethodCode;

    private String receiveMethodName;

    private String receiveMethodTypeName;

    private String registrantId;

    private Date registrationDateTime;

    private String recipientId;

    private Date receiptDateTime;
}
//>>> DDD / Value Object
