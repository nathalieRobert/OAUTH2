package fr.simplon.testApi.moneer.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.simplon.testApi.moneer.dtos.UserAccountCreateDto;
import fr.simplon.testApi.moneer.entities.UserAccount;
import fr.simplon.testApi.moneer.repositories.UserAccountRepository;


// UserDetailsService est une interface principale dans Spring Security. Elle
// est un service de recherche "Le compte d'utilisateur et ses rôles". Elle est
// utilisée par Spring Security chaque fois que l'utilisateur se connecte au
// système.
@Service
public class UserAccountServiceImpl
	implements UserAccountService, UserDetailsService {

    private final UserAccountRepository repo;

    private final PasswordEncoder encoder;

    public UserAccountServiceImpl(UserAccountRepository repo,
	    PasswordEncoder encoder) {
	super();
	this.repo = repo;
	this.encoder = encoder;
    }

    @Override
    public void create(UserAccountCreateDto dto) {
	UserAccount account = new UserAccount();
	account.setUsername(dto.getUsername());
	String password = dto.getPassword();
	String encodedPassword = encoder.encode(password);
	account.setPassword(encodedPassword);
	account.setRole(dto.getRole());
	account.setAccountNonExpired(dto.isAccountNonExpired());
	account.setAccountNonLocked(dto.isAccountNonLocked());
	account.setCredentialsNonExpired(dto.isCredentialsNonExpired());
	account.setEnabled(dto.isEnabled());
	repo.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	UserAccount account = repo.findByUsername(username);
	if (account == null) {
	    throw new UsernameNotFoundException(username);
	}
	UserBuilder builder = User.builder();
	builder.password(account.getPassword());
	builder.username(account.getUsername());
	builder.accountExpired(!account.isAccountNonExpired());
	builder.accountLocked(!account.isAccountNonLocked());
	builder.disabled(!account.isEnabled());
	builder.credentialsExpired(!account.isCredentialsNonExpired());
	// Role
	builder.authorities(account.getRole().name());
	return builder.build();
    }

    @Override
    public void deleteById(Long id) {
	repo.deleteById(id);
    }

  
}
