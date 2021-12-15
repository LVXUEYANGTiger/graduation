package yang.org.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class SwaggerResourcesConfig implements SwaggerResourcesProvider {

    @Value("${swagger.names}")
    private String[] apiNames;
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> collect = Arrays.asList(apiNames).stream().map(name -> {
            SwaggerResource swaggerResource = swaggerResource(name,  "/"+name+"/v2/api-docs");
            return swaggerResource;
        }).collect(Collectors.toList());

        return collect;
    }

    private SwaggerResource swaggerResource(String serviceId, String location){

        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(serviceId);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}