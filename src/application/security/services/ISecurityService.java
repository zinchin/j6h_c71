package application.security.services;

import java.util.List;
import java.util.Set;

import application.security.dto.AccountDto;

public interface ISecurityService {
	AccountDto addAccount(String login, String password, String role);
	AccountDto grantRole(String login, String role);
	AccountDto depriveRole(String login, String role);
	Set<String> getRolesByLogin(String login);
	List<AccountDto> getAllAccounts();
	AccountDto removeAccount(String login);
}