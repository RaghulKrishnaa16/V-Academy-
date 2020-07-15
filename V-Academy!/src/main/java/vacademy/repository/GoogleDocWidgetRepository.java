package vacademy.repository;

import javax.transaction.Transactional;

import vacademy.entity.GoogleDocWidget;

/**
 * @author Biswaraj
 *
 */
@Transactional
public interface GoogleDocWidgetRepository extends WidgetBaseRepository<GoogleDocWidget> {
}
