package com.example.ReservaIFPB_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Reserva IFPB API",
                description = "Documentação da API do sistema de reserva de espaços do IFPB.",
                version = "v1.0.0",
                contact = @Contact(
                        name = "Equipe ReservaIFPB",
                        email = "contato@reservaifpb.com.br"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor de Desenvolvimento Local")
        }
)
@Configuration
public class OpenApiConfig {

}