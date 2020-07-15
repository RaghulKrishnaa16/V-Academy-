
package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import vacademy.entity.HorizontalAlignment;


@Transactional
public interface HorizontalAlignmentRepository extends CrudRepository<HorizontalAlignment, Integer> {
}
