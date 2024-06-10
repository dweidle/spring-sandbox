# spring-sandbox




## Create new module
mvn archetype:generate -DarchetypeGroupId=com.baeldung.archetypes
-DarchetypeArtifactId=maven-archetype
-DarchetypeVersion=1.0-SNAPSHOT
-DgroupId=de.danielweidle.spring_sandbox
-DartifactId=hibernate-single-schema-multi-tenancy
-Dversion=1.0.0


## Run postgres

docker run -p 5432:5433:-e POSTGRES_PASSWORD=mysecretpassword -d postgres


mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DgroupId=de.danielweidle.spring_sandbox -DartifactId=spring-data-single-schema-multi-tenancy -Dversion=1.0.0