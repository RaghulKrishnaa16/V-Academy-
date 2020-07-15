
package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import vacademy.entity.VerticalAlignment;


@Transactional
public interface VerticalAlignmentRepository extends CrudRepository<VerticalAlignment, Integer> {
}
