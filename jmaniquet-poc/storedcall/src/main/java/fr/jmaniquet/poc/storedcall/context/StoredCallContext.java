package fr.jmaniquet.poc.storedcall.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.jmaniquet.poc.tools.core.context.ToolsCoreContext;

@Configuration
@Import(ToolsCoreContext.class)
@ComponentScan("fr.jmaniquet.poc.storedcall")
public class StoredCallContext {
}
