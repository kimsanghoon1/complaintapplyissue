package complaintapplyissue.domain;

import complaintapplyissue.domain.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "issurances",
    path = "issurances"
)
public interface IssuranceRepository
    extends PagingAndSortingRepository<Issurance, Long> {

        public Optional<Issurance> findByApplicationNumber(String applicationNumber);

    }
