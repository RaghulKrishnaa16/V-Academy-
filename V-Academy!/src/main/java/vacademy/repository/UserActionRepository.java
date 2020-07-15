
package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import vacademy.entity.UserAction;


@Transactional
public interface UserActionRepository extends CrudRepository<UserAction, Integer> {
}
