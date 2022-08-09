# frdemo

### Arquillian tests

```
mvn clean compile verify -Parq-managed 
```

### Setting up datasource

``` 
>  wget https://repo1.maven.org/maven2/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar  
> ./jboss-cli.sh
> module add --name=org.postgres --resources=postgresql-42.2.5.jar --dependencies=javax.api,javax.transaction.api
> /subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
> data-source add --jndi-name=java:/FRDemoDS --name=FRDemoDS --connection-url=jdbc:postgresql://localhost/frdemo --driver-name=postgres --user-name=frdemo --password=frdemo
```

### Wildfly deploy

```
mvn clean install wildfly:deploy
```

