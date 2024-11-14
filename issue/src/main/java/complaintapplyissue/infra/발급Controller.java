package complaintapplyissue.infra;

import complaintapplyissue.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/발급")
@Transactional
public class 발급Controller {

    @Autowired
    발급Repository 발급Repository;
}
//>>> Clean Arch / Inbound Adaptor
