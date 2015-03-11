package fr.jmaniquet.poc.storedcall.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.jmaniquet.poc.tools.context.ToolsContext;

@Configuration
@Import(ToolsContext.class)
@ComponentScan("fr.jmaniquet.poc.storedcall")
public class StoredCallContext {
}
