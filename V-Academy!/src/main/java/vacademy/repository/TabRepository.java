package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import vacademy.entity.Tab;


@Transactional
public interface TabRepository extends CrudRepository<Tab, Integer> {
	@Query(value="DELETE t FROM Tab AS t WHERE t.tabId = :tabId", nativeQuery=true)
	@Modifying
	void deleteByTabId(@Param("tabId") int tabId);
}
