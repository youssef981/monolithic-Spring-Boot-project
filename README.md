# monolithic-Spring-Boot-project
Here's a refactored and improved version of your README instructions, focusing on clarity, conciseness, and better organization:

**README.md**

## Dockerizing The Spring Boot Application

**Prerequisites**

* Java Development Kit (JDK)
* Maven
* Docker 

**Understanding the Basics**

* **Spring Boot:** A Java-based framework for creating production-ready applications.
* **Maven:** A project build and dependency management tool.
* **Docker:** A platform for packaging and running applications within isolated environments (containers).

**Steps**

1. **Create a Dockerfile:**

   ```dockerfile
   FROM openjdk:8-jdk-alpine
   LABEL maintainer="your.email@example.com"
   VOLUME /tmp
   EXPOSE 8080 
   ARG JAR_FILE=target/spring-boot-app-1.0.0.jar
   ADD ${JAR_FILE} app.jar
   ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
   ```

   *This Dockerfile specifies the base image, exposes the application's port, copies your JAR file, and defines the startup command.*

2. **Build the Docker Image:**

   ```bash
   docker build -t spring-boot-app . 
   ```

   *This builds the image with the name `spring-boot-app`.*

3. **Run the Container**

   ```bash
   docker run -p 8080:8080 spring-boot-app
   ```

   *This runs the container, mapping port 8080 of your host machine to the container's exposed port.*

4. **Verify**

   Access your application in your web browser at  `http://localhost:8080`.

**Explanation of Dockerfile Instructions**

* `FROM`: Specifies the base image (here, OpenJDK 8).
* `LABEL`: Adds metadata (like the maintainer's email).
* `VOLUME`: Creates a mount point for temporary data.
* `EXPOSE`:  Declares the port the application listens on.
* `ARG`: Defines a build-time variable for the JAR file path.
* `ADD`: Copies the built JAR file into the image.
* `ENTRYPOINT`: Specifies the command to execute when the container starts.


## PostgreSQL & pgAdmin with Docker Compose
Docker Compose simplifies managing multiple containers by allowing you to define their configurations in a single YAML file. Here's how you can combine the PostgreSQL and pgAdmin containers into a Docker Compose configuration:

1. **Create a Docker Compose YAML file**: Create a file named **`docker-compose.yml`**.
2. **Define services**: In the YAML file, define the services you want to run.

```yaml
yamlCopy code
version: '3.8'

services:
  postgres:
    image: postgres
    container_name: postgres_container
    environment:
      POSTGRES_USER: embarkx
      POSTGRES_PASSWORD: embarkx
      PGDATA: /data/postgres
    volumes:
      - postgres:/data/postgres
    ports:
      - "5432:5432"
    networks:
      - postgres
    restart: unless-stopped

  pgadmin:
    image: dpage/pgadmin4
    container_name: pgadmin_container
    environment:
      PGADMIN_DEFAULT_EMAIL: pgadmin4@pgadmin.org
      PGADMIN_DEFAULT_PASSWORD: admin
      PGADMIN_CONFIG_SERVER_MODE: 'False'
    volumes:
      - pgadmin:/var/lib/pgadmin
    ports:
      - "5050:80"
    networks:
      - postgres
    restart: unless-stopped

networks:
  postgres:
    driver: bridge

volumes:
  postgres:
  pgadmin:

```

Let's break down what's happening here:

- **`version`**: Specifies the version of the Docker Compose syntax.
- **`services`**: Defines the services you want to run. In this case, **`postgres`** and **`pgadmin`**.
- For each service:
    - **`image`**: Specifies the Docker image to use.
    - **`container_name`**: Specifies the name of the container.
    - **`environment`**: Sets environment variables required by the container.
    - **`volumes`**: Mounts volumes required by the container.
    - **`ports`**: Exposes ports from the container to the host machine.
    - **`networks`**: Specifies the network to which the container should be connected.
    - **`restart`**: Specifies the restart policy for the container.
- **`networks`**: Defines the Docker network.
- **`volumes`**: Defines the volumes required by the services.

1. **Run Docker Compose**: In the directory containing the **`docker-compose.yml`** file, run:

```bash
bashCopy code
docker-compose up -d
```

This command will start the defined services in detached mode (background).

With Docker Compose, you can easily manage the configurations of multiple containers, making it simple to start, stop, and modify your application stack. You can customize the configuration by modifying the YAML file as needed.

> **Notions:**

> > server mode vs standalone mode : ****In pgAdmin, "server mode" refers to a feature that allows pgAdmin to be run as a web application accessible by multiple users. When pgAdmin is in server mode, it provides a web interface that multiple users can access to manage their PostgreSQL databases.However, if you set `PGADMIN_CONFIG_SERVER_MODE=False`, you are disabling this server mode. In other words, you're configuring pgAdmin to run in standalone mode instead of server mode. In standalone mode, pgAdmin is typically used by a single user to manage their local PostgreSQL instance.
> > 
> 
> > **Mounting a volume in Docker** means making a directory from the host machine (the machine running Docker) available inside the container. It enables persistent storage for data and files generated or used by processes running inside the container.  When you mount a volume, Docker creates a link between a directory on the host machine and a directory inside the container. Any changes made to files in this directory, either from the host or the container, are reflected in both places.
> >
