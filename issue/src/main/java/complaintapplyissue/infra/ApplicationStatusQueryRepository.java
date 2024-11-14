package complaintapplyissue.infra;

import complaintapplyissue.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "applicationStatusQueries",
    path = "applicationStatusQueries"
)
public interface ApplicationStatusQueryRepository
    extends PagingAndSortingRepository<ApplicationStatusQuery, Long> {}
