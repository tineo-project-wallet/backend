package com.tineo.wallet_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Wallet Backend API",
                version = "1.0",
                description = "Endpoints for the Wallet Backend API",
                contact = @Contact(
                        name = "TineoJF - Backend Developer",
                        url = "https://github.com/tineojf"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local server"
                )
        }
)
public class OpenAPIConfig {
}
