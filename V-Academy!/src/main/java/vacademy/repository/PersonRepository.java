package vacademy.repository;

import javax.transaction.Transactional;

import vacademy.entity.Person;

/**
 * @author dey
 *
 */
@Transactional
public interface PersonRepository extends BasePersonRepository<Person> {
}
