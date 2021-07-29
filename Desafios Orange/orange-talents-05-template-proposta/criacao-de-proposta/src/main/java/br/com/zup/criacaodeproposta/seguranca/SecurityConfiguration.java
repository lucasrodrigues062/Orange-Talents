package br.com.zup.criacaodeproposta.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests( authorizeRequest ->
                authorizeRequest
                        .antMatchers(HttpMethod.POST, "/api/proposta/**").hasAuthority("SCOPE_proposta-scope")
                        .antMatchers(HttpMethod.GET, "/api/proposta/**").hasAuthority("SCOPE_proposta-scope")
                        .antMatchers(HttpMethod.POST, "/api/biometria/**").hasAuthority("SCOPE_proposta-scope")
                        .antMatchers(HttpMethod.GET, "/api/biometria/**").hasAuthority("SCOPE_proposta-scope")
                        .antMatchers(HttpMethod.POST, "/api/bloqueio/**").hasAuthority("SCOPE_proposta-scope")
                        .antMatchers(HttpMethod.POST, "/api/avisoviagem/**").hasAuthority("SCOPE_proposta-scope")
                        .antMatchers(HttpMethod.GET, "/actuator/prometheus/**").permitAll()
                        .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }
}
