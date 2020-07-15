
package vacademy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import vacademy.entity.Widget;


@NoRepositoryBean
public interface WidgetBaseRepository<T extends Widget> extends CrudRepository<Widget, Integer> {
}
