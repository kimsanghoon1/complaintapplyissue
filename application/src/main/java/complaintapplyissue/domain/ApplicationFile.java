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

    private String fileId;

    private String fileName;

    private String fileExtension;

    private FileType fileTypeCode;

    private String fileOrder;

    private Boolean isActive;

    private Date createdDate;

    private Date modifiedDate;
}
//>>> DDD / Value Object
