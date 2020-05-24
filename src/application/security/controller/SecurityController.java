package application.security.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.security.dto.AccountDto;
import application.security.dto.AuthPair;
import application.security.services.ISecurityService;

@RestController
@RequestMapping("/security")
@CrossOrigin
public class SecurityController {
	
	@Autowired ISecurityService service;

	@PostMapping("/addAccount")
	public AccountDto addAccount(@RequestBody AuthPair authPair, @RequestParam String role) {
		return service.addAccount(authPair.getLogin(), authPair.getPassword(), role);
	};

	@PutMapping("/grantRole")
	public AccountDto grantRole(String login, String role) {
		return service.grantRole(login, role);
	};
	
	@PutMapping("/depriveRole")
	public AccountDto depriveRole(String login, String role) {
		return service.depriveRole(login, role);
	};
	
	@GetMapping("/getRolesByLogin")
	public Set<String> getRolesByLogin(String login) {
		return service.getRolesByLogin(login);
	}
	
	@GetMapping("/getAllAccounts")
	public List<AccountDto> getAllAccounts(){
		return service.getAllAccounts();
	};

	@DeleteMapping("/removeAccount")
	public AccountDto removeAccount(String login) {
		return service.removeAccount(login);
	};
}
