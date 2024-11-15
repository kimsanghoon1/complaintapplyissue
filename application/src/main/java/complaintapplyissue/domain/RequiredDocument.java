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
public class RequiredDocument {

    private String fileId;

    private String fileName;

    private String filePathName;

    private String fileExtensionName;

    private String fileSize;

    private String fileOrder;

    private Boolean isActive;

    private FileType fileType;
}
//>>> DDD / Value Object
