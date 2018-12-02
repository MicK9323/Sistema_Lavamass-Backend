package com.mca.apps.lavamassapirest.security.config;

import com.mca.apps.lavamassapirest.security.CustomUserDetailsService;
import com.mca.apps.lavamassapirest.security.JwtAuthenticationEntryPoint;
import com.mca.apps.lavamassapirest.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Miguel Cortegana
 * @version 1.0.0
 * -EnableWebSecurity: Habilita la seguridad web en el proyecto.
 * -securedEnabled: Habilita el uso de la anotación @Secured para proteger los métodos
 * de los controladores y servicios.
 * -jsr250Enabled: Habilita el uso de la anotación @RolesAllowed.
 * -prePostEnabled: Permite un uso más complejo de las expresiones de control
 * con las anotaciones @Preauthorize y @PostAuthorize.
 * -WebSecurityConfigurerAdapter: Esta clase implementa de la interfaz WebSecurityConfigurer
 * la cual provee de una configuración de seguridad por defecto y permite que otras clases lo
 * hereden y customizen la seguridad sobrescribiendo sus métodos.
 * -JwtAuthenticationEntryPoint: Esta clase es usada para retornar un error 401 a los clientes
 * que intenten acceder a un recurso protegido si haberse autenticado. Implementa de la interfaz
 * AuthenticationEntryPoint.
 * -JwtAuthenticationFilter
 * Usamos esta clase para implementar un filtro que:
 *  ->Lea los jwt de las cabeceras de las peticiones.
 *  ->Valide los token.
 *  ->Cargar el detalle del usuario asociado al token.
 *  ->Poner al usuario en el contexto de Spring Security.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers("/",
                            "/favicon.ico",
                            "/**/*.png",
                            "/**/*.gif",
                            "/**/*.svg",
                            "/**/*.jpg",
                            "/**/*.html",
                            "/**/*.css",
                            "/**/*.js")
                            .permitAll()
                    .antMatchers("/api/v1/auth/**")
                            .permitAll()
                    .antMatchers("/api/v1/user/checkEmailAvailability")
                            .permitAll()
                    .antMatchers(HttpMethod.GET, "/api/v1/parameters/**")
                            .permitAll()
                    .anyRequest()
                            .authenticated();

        // Add custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
