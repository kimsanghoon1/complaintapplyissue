package complaintapplyissue.infra;

import complaintapplyissue.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class IntegrationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Integration>> {

    @Override
    public EntityModel<Integration> process(EntityModel<Integration> model) {
        return model;
    }
}
