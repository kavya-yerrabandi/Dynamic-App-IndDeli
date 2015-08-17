
package rest.resrvtn.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class INDAppConfig extends ResourceConfig {
	
	public INDAppConfig(){
		packages("rest.resrvtn.api");
	}

}
