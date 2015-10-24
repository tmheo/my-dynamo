package tmheo.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import tmheo.model.Person;

/**
 * Created by taemyung on 2015. 10. 24..
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, String> {

    @EnableScan
    @EnableScanCount
    Page<Person> findAll(Pageable pageable);

}
