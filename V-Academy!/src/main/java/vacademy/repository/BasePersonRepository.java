package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import vacademy.entity.Person;

/**
 * @author dey
 *
 */
@NoRepositoryBean
@Transactional
public interface BasePersonRepository<T extends Person> extends CrudRepository<T, Integer> {
}
