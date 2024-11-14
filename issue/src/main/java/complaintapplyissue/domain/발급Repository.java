package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "발급", path = "발급")
public interface 발급Repository
    extends PagingAndSortingRepository<발급, Long> {}
