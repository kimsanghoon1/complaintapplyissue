package complaintapplyissue.infra;

import complaintapplyissue.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class IssuranceHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Issurance>> {

    @Override
    public EntityModel<Issurance> process(EntityModel<Issurance> model) {
        return model;
    }
}
