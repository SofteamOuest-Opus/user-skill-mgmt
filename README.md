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

## Comment ajouter un service REST

1. mettre à jour le fichier [openapi.yaml](user-skill-mgmt-api/src/main/resources/openapi.yaml) en définissant le bus d'événnements Vertx (x-vertx-event-bus)
1. définir l'interface du service dans le package services (voir [VersionService.java](user-skill-mgmt-api/src/main/java/fr/softeam/opus/userskillmgmt/services/VersionService.java) )
1. implémenter le service dans le package business (voir [VersionBlo.java](user-skill-mgmt-api/src/main/java/fr/softeam/opus/userskillmgmt/business/version/VersionBlo.java))
1. configurer le binder guice dans [BeansBinderConfig.java](user-skill-mgmt-api/src/main/java/fr/softeam/opus/userskillmgmt/configuration/BeansBinderConfig.java)
1. enregistrer le service sur le bus d'événnements de l'étape 1 dans [UserSkillMgmtVerticle.java](user-skill-mgmt-api/src/main/java/fr/softeam/opus/userskillmgmt/UserSkillMgmtVerticle.java)
 