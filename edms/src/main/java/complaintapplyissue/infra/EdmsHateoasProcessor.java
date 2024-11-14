package complaintapplyissue.infra;

import complaintapplyissue.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class EdmsHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Edms>> {

    @Override
    public EntityModel<Edms> process(EntityModel<Edms> model) {
        return model;
    }
}
