package complaintapplyissue.infra;

import complaintapplyissue.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class 발급HateoasProcessor
    implements RepresentationModelProcessor<EntityModel<발급>> {

    @Override
    public EntityModel<발급> process(EntityModel<발급> model) {
        return model;
    }
}
