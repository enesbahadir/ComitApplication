package bdd;

import com.comit.ComitApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ComitApplication.class)
public class SpringBootCucumberTestConfiguration {
}