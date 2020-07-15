
package vacademy.service;

import java.util.Collection;

import vacademy.entity.Person;


public interface IPersonService<T extends Person> {
	public T getById(int id);

	public Collection<T> getAllPersons();

	public T add(T person);

	public T update(T person);

	public void delete(T person);
}
