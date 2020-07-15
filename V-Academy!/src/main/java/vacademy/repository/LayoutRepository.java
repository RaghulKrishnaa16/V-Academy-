package vacademy.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import vacademy.entity.Layout;

/**
 * @author biswaraj
 *
 */
@Transactional
public interface LayoutRepository extends CrudRepository<Layout, Integer> {
}
