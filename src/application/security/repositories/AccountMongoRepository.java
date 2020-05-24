package application.security.repositories;

import application.common.CustomizedMongoRepository;
import application.security.documents.Account;

public interface AccountMongoRepository extends CustomizedMongoRepository<Account, String>{
	
	default String type() {
		return "Account";
	}

}
