FROM jboss/keycloak:15.1.1

ENV KEYCLOAK_USER=admin
ENV KEYCLOAK_PASSWORD=admin
ENV DB_ADDR=pg-keycloak
ENV DB_PORT=5432
ENV DB_USER=keycloak
ENV DB_PASSWORD=keycloak
ENV DB_DATABASE=keycloak
ENV DB_VENDOR=postgres
ENV PROXY_ADDRESS_FORWARDING=true

# 1. EXTENSIONS
# 1.1 User management API
COPY ./assemblies/user-management/target/user-management-extension.jar /opt/jboss/keycloak/standalone/deployments
# 1.2 Simple logger
COPY ./assemblies/simple-logger/target/simple-logger-extension.jar /opt/jboss/keycloak/standalone/deployments

# 2. THEMES
# 2.1 mJamsek theme
RUN mkdir /opt/jboss/keycloak/themes/mjamsek-theme
COPY ./themes/mjamsek-theme/ /opt/jboss/keycloak/themes/mjamsek-theme/
