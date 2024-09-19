# Quotly Backend

This project uses Quarkus as Java Framework.

Quarkus documentation: <https://quarkus.io/>

## Commit guidelines

> ```<ticket-id>: commit message``` Example: ```#1: Added mushrooms```

## Coding guidelines

### Code style

Use the "intellij-code-style.xml" as default code style in IntelliJ

> Settings > Editor > Code Style > Java - Import Scheme

### Usefully IntelliJ plugins

| Plugin name      | Author         | Purpose                                    |
|------------------|----------------|--------------------------------------------|
| Rainbow Brackets | izhangzhihao   | Better code readability                    |
| Indent Rainbow   | Indent Rainbow | Better code readability                    |
| EnvFile          | Borys Pierov   | Run application with environment variables |

## Using environment file locally

Create a file named exactly like ```.env``` in the root directory of the repository.
Then put there your custom environment variables like ```SERVER_PORT=8080``` and start the application.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quotly-backend-1.0-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- MongoDB with Panache ([guide](https://quarkus.io/guides/mongodb-panache)): Simplify your persistence code for MongoDB
  via the active record or the repository pattern
- REST JSON-B ([guide](https://quarkus.io/guides/rest#json-serialisation)): JSON-B serialization support for Quarkus
  REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on
  it.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
