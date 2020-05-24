package application.common;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import application.common.exceptions.DuplicatedIdException;
import application.common.exceptions.IdNotFoundException;

@NoRepositoryBean
public interface CustomizedMongoRepository<T,ID> extends MongoRepository<T, ID>{

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
