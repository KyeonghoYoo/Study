package me.kyeongho.account;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService{

	@Autowired
	private AccountRepository accountRepository;

	public Account createAccount(String userName, String password) {
		Account account = new Account();
		account.setUserName(userName);
		account.setPassword(password);
		return accountRepository.save(account);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> byUserName = accountRepository.findByUserName(username);
		Account account = byUserName.orElseThrow(() -> new UsernameNotFoundException(username));
		return new User(account.getUserName(), account.getPassword(), authorities());
	}

	private Collection<? extends GrantedAuthority> authorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
}
