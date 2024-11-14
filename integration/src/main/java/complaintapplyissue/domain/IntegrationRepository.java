package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "integrations",
    path = "integrations"
)
public interface IntegrationRepository
    extends PagingAndSortingRepository<Integration, Long> {}
