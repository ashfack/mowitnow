# HowTo

Ce document donne quelques informations sur les librairies utilisées (dépendances) et explique comment lancer le projet.

(Il est préférable d'avoir lu le ReadMe avant)

Il s'agit d'un projet maven minimaliste que l'on pourra étendre par la suite avec Spring pour par exemple envoyer les instructions via une méthode Post.

Le projet utilise :
- lombok pour les annotations (Getter, Setter, ...) cela permet d'avoir des modèles plus lisibles et il facilite la création d'un logger
- les logger slf4j
- Junit5 pour les tests

Si vous utilisez un ide il y a une installation à faire pour lombok https://projectlombok.org/.

Les tests unitaires sont placés dans src/test/java.
**Dans ces tests unitaires on charge les fichiers et il est important de le faire depuis windows ou de changer les chemins.**

Pour compiler, une fois dans le répertoire avec le pom.xml faire :
```shell
mvn -B package -f pom.xml
```


Pour lancer, faire après avoir récupéré le jar dans target:
```shell
java -jar mow-0.0.1.jar ../src/main/resources/xebia.txt
```

Ou vous pouvez mettre un chemin absolu.

Vous pouvez changer le niveau de log (conf de log) en utilisant un fichier de configuration externe comme simplelogger.properties présent dans les ressources.

Il y a également grace aux github actions dans ce projet une compilation automatique à chaque push sur master et chaque merge request dessus.
