package application.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import application.common.exceptions.DuplicatedIdException;
import application.common.exceptions.IdNotFoundException;

@NoRepositoryBean
public interface CustomizedJpaRepository<T, ID> extends JpaRepository<T, ID>{
	
	default String type() {
		return "Entity";
	}
	
	default T checkById(ID id, boolean exists) {
		T entity = findById(id).orElse(null);
		if (entity == null && exists)
			throw new IdNotFoundException(type()+" with id "+id+ " not found");
		else if (entity != null && !exists)
			throw new DuplicatedIdException("Duplicated "+type()+" with id "+id);	
		return entity;
	}
}
