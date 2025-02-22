package br.com.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class securityResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                    .antMatchers(HttpMethod.POST, "/api/proposal").hasAuthority("SCOPE_proposal-scopes")
                    .antMatchers(HttpMethod.GET, "/api/proposal/**").hasAuthority("SCOPE_proposal-scopes:read")
                    .antMatchers(HttpMethod.POST, "/api/biometry").hasAuthority("SCOPE_proposal-scopes:write")
                    .antMatchers(HttpMethod.POST, "/api/biometry/**").hasAuthority("SCOPE_proposal-scopes:write")
                    .antMatchers(HttpMethod.GET, "/api/card/**").hasAuthority("SCOPE_proposal-scopes:read")
                    .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
