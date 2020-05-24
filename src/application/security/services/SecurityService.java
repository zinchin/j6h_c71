package application.security.services;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import application.security.documents.Account;
import application.security.dto.AccountDto;
import application.security.dto.SecurityDtoService;
import application.security.exceptions.AccountManagementException;
import application.security.repositories.AccountMongoRepository;

@Transactional
@Service
public class SecurityService implements ISecurityService {
	
	@Autowired AccountMongoRepository accountRepo;
	@Autowired SecurityDtoService dtoService;
	@Autowired PasswordEncoder encoder;

	private Account getAccount(String login) {
		return accountRepo.checkById(login, true);
	}

	@Override
	public AccountDto addAccount(String login, String password, String role) {
		
		accountRepo.checkById(login, false);		
		Account account = new Account(login, encoder.encode(password), "ROLE_"+role);
		accountRepo.save(account);
		return dtoService.accountDto(account);
	}
	
	@Override
	public AccountDto grantRole(String login, String role) {
		
		Account account = getAccount(login);
		account.getRoles().add("ROLE_"+role);
		accountRepo.save(account);
		
		return dtoService.accountDto(account);
	}
		
	@Override
	public AccountDto depriveRole(String login, String role) {
		
		Account account = getAccount(login);
		
		Set<String> roles = account.getRoles();
		if (roles.size() < 2)
			throw new AccountManagementException("Account "+login+" has only one role. Accounts without roles are not allowed.");
		String prefixedRole = "ROLE_"+role;
		if (!roles.contains(prefixedRole))
			throw new AccountManagementException("Account "+login+" has no role "+role);
		roles.remove(prefixedRole);
		
		accountRepo.save(account);
		return dtoService.accountDto(account);
	}

	@Override
	public Set<String> getRolesByLogin(String login) {
		return getAccount(login).getRoles();
	}
	
	
	@Override
	public List<AccountDto> getAllAccounts() {
		return dtoService.accountDtoList(accountRepo.findAll());
	}

	@Override
	public AccountDto removeAccount(String login) {
		Account account = getAccount(login);
		accountRepo.deleteById(login);
		return dtoService.accountDto(account);
	}
}
