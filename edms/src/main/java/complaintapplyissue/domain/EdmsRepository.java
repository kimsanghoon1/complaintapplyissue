package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "edms", path = "edms")
public interface EdmsRepository
    extends PagingAndSortingRepository<Edms, Long> {}
