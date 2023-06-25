package com.example.config;

import com.example.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AppConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/","/api/admin_home","/api/manage_movie","/api/manage_schedule","/api/manage_user","/api/manage_food","/api/manage_ticket","/api/manage_satistics").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/register").permitAll() // allow unauthenticated access to /register
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/api/login")
                .usernameParameter("username") // set the input name for the username field
                .passwordParameter("password") // set the input name for the password field
                .defaultSuccessUrl("/api/admin_home")
                .failureUrl("/api/login?error=true")
                .and()
                .logout()
                .logoutUrl("/Home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/Home")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/api/login?error=true")
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}

/*
 Đầu tiên, @EnableWebSecurity được sử dụng để kích hoạt cấu hình bảo mật web.
 Sau đó, configureGlobal được sử dụng để cấu hình xác thực người dùng bằng cách sử dụng CustomUserDetailsService và mã hóa mật khẩu.

Trong phương thức securityFilterChain, các yêu cầu HTTP được ủy quyền bằng cách sử dụng phương thức authorizeHttpRequests.
Các yêu cầu đến các đường dẫn như /api/admin_home, /api/manage_movie, v.v. chỉ được phép truy cập bởi người dùng có vai trò ADMIN.
Các yêu cầu đến /register và /login được phép truy cập mà không cần xác thực. Các yêu cầu khác được phép truy cập.

Cấu hình đăng nhập và đăng xuất cũng được thực hiện trong phương thức này. Đăng nhập được thực hiện thông qua trang /api/login với các tham số đầu vào là username và password.
Khi đăng nhập thành công, người dùng sẽ được chuyển hướng đến trang /api/admin_home. Khi đăng xuất, người dùng sẽ được chuyển hướng đến trang /Home.

CustomUserDetailsService là một lớp dịch vụ được sử dụng để tải thông tin người dùng từ cơ sở dữ liệu bằng cách sử dụng UserRepository. Lớp này triển khai giao diện UserDetailsService của Spring Security.

Phương thức loadUserByUsername được ghi đè để tải thông tin người dùng từ cơ sở dữ liệu bằng cách sử dụng tên người dùng. Nếu người dùng không tồn tại, nó sẽ ném ra một ngoại lệ UsernameNotFoundException. Nếu người dùng tồn tại, thông tin người dùng sẽ được trả về dưới dạng một đối tượng UserDetails.

Phương thức mapRolesToAuthorities được sử dụng để chuyển đổi các vai trò của người dùng thành các quyền được cấp (GrantedAuthority). Các quyền này được sử dụng để kiểm tra quyền truy cập của người dùng đến các tài nguyên được bảo vệ.

*/
