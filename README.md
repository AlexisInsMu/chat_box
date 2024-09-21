# Mricoservicio con springboot para la implementacion de inteligencia artifical generativa Microservicio de Chat con Spring Boot
Este proyecto es un microservicio desarrollado con Spring Boot que integra inteligencia artificial generativa para manejar interacciones de chat. Permite recibir solicitudes de chat y responder utilizando un modelo de IA con restricciones para que unicamente acepte los temas relacionados con guias de turismo.

Estructura del Proyecto
El proyecto está estructurado de la siguiente manera:

com.cooltur.cooltur

Contiene la clase principal CoolturApplication que inicia la aplicación.
Contiene la clase de prueba CoolturApplicationTests que verifica el contexto de la aplicación.
com.cooltur.cooltur.controller

Contiene ChatController, que maneja las solicitudes de chat.
com.cooltur.cooltur.domain.chat

Define las clases relacionadas con el dominio del chat, como Chat, ChatBody, ChatRespuesta, y ChatReturn.
Funcionalidades
Recibir Solicitudes de Chat: Permite a los usuarios enviar mensajes al servicio.
Generar Respuestas: Utiliza un modelo de inteligencia artificial generativa para crear respuestas contextuales.
Instalación
Clona el repositorio:

bash
Copiar código
git clone https://github.com/tu_usuario/cooltur.git
cd cooltur
Asegúrate de tener instalado Java y Maven.

Compila y ejecuta la aplicación:

bash
Copiar código
mvn spring-boot:run
Uso
El microservicio expone un endpoint REST para interactuar con el chat:

Endpoint: POST /chat
Cuerpo de la Solicitud:
json
Copiar código
{
    "mensaje": "Tu mensaje aquí"
}
Respuesta:
json
Copiar código
{
    "respuesta": "Respuesta generada por la IA"
