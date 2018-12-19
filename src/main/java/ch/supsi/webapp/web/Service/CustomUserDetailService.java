package ch.supsi.webapp.web.Service;



import ch.supsi.webapp.web.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private BlogPostService blogService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = blogService.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList (user.getRole().getName());
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, auth);
	}

}