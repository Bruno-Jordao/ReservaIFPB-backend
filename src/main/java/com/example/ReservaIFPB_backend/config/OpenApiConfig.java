package com.example.ReservaIFPB_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Reserva IFPB API",
                description = "Documentation for the IFPB space reservation system API",
                version = "v1.0.0",
                contact = @Contact(
                        name = "Team ReservaIFPB",
                        email = "contact@reservaifpb.com.br"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local Development Server")
        }
)
@Configuration
public class OpenApiConfig {

}