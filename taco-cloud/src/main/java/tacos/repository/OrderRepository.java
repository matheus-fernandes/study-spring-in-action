package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
