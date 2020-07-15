package vacademy.repository;

import javax.transaction.Transactional;

import vacademy.entity.ImageWidget;

/**
 * @author Biswaraj
 *
 */
@Transactional
public interface ImageWidgetRepository extends WidgetBaseRepository<ImageWidget> {
}
