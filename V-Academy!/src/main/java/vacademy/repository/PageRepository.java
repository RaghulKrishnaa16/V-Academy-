package vacademy.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import vacademy.entity.Page;


@Transactional
public interface PageRepository extends CrudRepository<Page, Integer> {
	@Query(value="DELETE p FROM Page AS p WHERE p.pageId = :pageId", nativeQuery=true)
	@Modifying
	void deleteByPageId(@Param("pageId") int pageId);
	
	@Query(value="UPDATE Page p SET p.name=:name, p.tooltipDescription=:tooltipDescription WHERE p.pageId = :pageId", nativeQuery=true)
	@Modifying
	void updateByPageId(@Param("pageId") int pageId, @Param("name")String name, @Param("tooltipDescription") String tooltipDescription);
}
