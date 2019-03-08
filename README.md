# user-skill-mgmt

## API
Le code source de l'API se trouve dans le répertoire user-skill-mgmt-api.

## Client
Le code source du client se trouve dans le répertoire user-skill-mgmt-client.

## Compiler
```zsh
./mvnw clean install
```

## Lancer le serveur verticle
Lancer la classe UserSkillMgmtLauncher avec les params de JVM:
```zsh
-Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.SLF4JLogDelegateFactory
```