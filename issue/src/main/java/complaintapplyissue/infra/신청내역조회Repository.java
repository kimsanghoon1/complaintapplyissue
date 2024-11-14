package complaintapplyissue.infra;

import complaintapplyissue.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "신청내역조회",
    path = "신청내역조회"
)
public interface 신청내역조회Repository
    extends PagingAndSortingRepository<신청내역조회, Long> {}
