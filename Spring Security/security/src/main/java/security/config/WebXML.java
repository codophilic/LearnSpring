package security.config;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;
import security.SpringConfiguration;
import security.SpringSecurityConfig;

// This class serves as a replacement for the traditional web.xml file. 
// It initializes the Spring DispatcherServlet and specifies the configuration classes.

public class WebXML extends AbstractAnnotationConfigDispatcherServletInitializer {

    // This method returns the configuration classes for the root application context.
    // The root application context typically contains beans that are shared across the entire application.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SpringConfiguration.class,SpringSecurityConfig.class }; //spring-servlet.xml
    }

    // This method returns the configuration classes for the DispatcherServlet application context.
    // Since it returns null here, it means all configurations are provided by the root context.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    // This method specifies the URL patterns that the DispatcherServlet will be mapped to.
    // In this case, it maps the DispatcherServlet to the root URL pattern ("/"),
    // meaning it will handle all incoming requests.
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        // Registering the DelegatingFilterProxy to delegate filter processing to Spring Security
        return new Filter[] { new DelegatingFilterProxy("springSecurityFilterChain") };
    }
}
