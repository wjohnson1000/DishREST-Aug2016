package resouces;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    private Set<Class<?>> resourceSet = new java.util.HashSet<>();
    {
        resourceSet.add(GenericResource.class);
        resourceSet.add(org.codehaus.jackson.jaxrs.JacksonJsonProvider.class);
        resourceSet.add(exceptionmappers.NotFoundExceptionMapper.class);
    }
    
    @Override
    public Set<Class<?>> getClasses() {
        return resourceSet;
    }
}
