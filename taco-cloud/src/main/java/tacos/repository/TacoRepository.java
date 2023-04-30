package tacos.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import tacos.entity.Taco;

import java.util.List;

@Repository
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

    String FIELD_CREATED_AT = "createdAt";

    default List<Taco> findRecent(Integer pageSize){
        Pageable pageable = PageRequest
                .ofSize(pageSize)
                .withPage(0)
                .withSort(Sort.by(FIELD_CREATED_AT).descending());

        return this.findAll(pageable).getContent();
    }
}
