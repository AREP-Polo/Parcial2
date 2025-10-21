# Arquitectura de Microservicios - Lucas Sequence

**Autor:** Marianella Polo Peña  
**Institución:** Escuela Colombiana de Ingeniería  
**Materia:** Arquitecturas Empresariales
## Descripción del Proyecto

Se implementó una arquitectura de microservicios desplegada en AWS EC2 para computar la secuencia de Lucas. El sistema consta de tres componentes:

1. **Servicio de Matemáticas (2 instancias):** Calcula la función de secuencia de Lucas.
2. **Servicio Proxy (1 instancia):** Distribuye las solicitudes a las instancias del servicio matemático usando round-robin, y sirve el cliente web.
3. **Cliente Web:** Interfaz HTML5 y JavaScript que realiza llamadas asincrónicas al proxy.

## Tecnologías

- Maven
- Spring Boot 3
- Java 17
- HTML5
- JavaScript
- AWS EC2

## Pasos de Implementación en AWS EC2

### 1. Configuración de Red en AWS

- Crear una VPC específica para el proyecto
- Crear una Subnet dentro de la VPC
- Crear una Routing Table
- Crear un Internet Gateway público
- Crear un grupo de seguridad con reglas para SSH, HTTP y HTTPS

### 2. Creación de Instancias EC2

- Crear 3 instancias de EC2 dentro de la VPC
- Asignar IPs privadas a las instancias de servicio matemático
- Asignar una IP pública a la instancia del proxy
- Conectarse a cada instancia vía SSH

### 3. Instalación de Dependencias

En cada instancia, ejecutar:

```bash
sudo yum install -y git
sudo yum install -y java-17-amazon-corretto-devel
sudo dnf install -y maven
```

### 4. Despliegue del Proyecto

En cada instancia:

```bash
git clone https://github.com/AREP-Polo/Parcial2
cd arep-parcial-2
```

**En las 2 instancias de Math Service:**

```bash
cd lucas-seq
mvn spring-boot:run
```

**En la instancia del Proxy:**

```bash
cd proxy
mvn spring-boot:run
```

Todas las aplicaciones se ejecutan en el puerto 80.

### 5. Acceso a la Aplicación

Acceder desde el navegador usando la IP pública o DNS de la instancia del proxy para visualizar el cliente web y probar la funcionalidad.

## Video de Demostración

## Notas Técnicas

- El proxy utiliza variables de entorno del sistema operativo para configurar las direcciones IP y puertos de las instancias del servicio matemático.
- La comunicación entre el proxy y los servicios matemáticos se realiza mediante HTTP.
- El cliente realiza peticiones asincrónicas utilizando XMLHttpRequest.
