# Règles de Gestion

Afin de permettre un suivi des implémentations des fonctionnalités et contraintes, ce document reprendra les différentes règles données avec un numéro de règle qui figureront dans le code.

**RG 001**:
>La société MowItNow a décidé de développer une tondeuse à gazon automatique, destinée aux surfaces rectangulaires.

**RG 002**:
>La position de la tondeuse est représentée par une combinaison de coordonnées (x,y).

**RG 003**:
>La position de la tondeuse est représentée par une lettre indiquant l'orientation.

**RG 004**:
>L'orientation selon la notation cardinale anglaise (N,E,W,S).

**RG 005**:
>« 0, 0, N » signifie que la tondeuse est dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.

**RG 006**:
>Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres.

**RG 007**:
>Les lettres possibles sont « D », « G » et « A ».

**RG 008**:
>Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge pas,
conserve son orientation et traite la commande suivante.

**RG 009**:
>La case directement au Nord de la position (x, y) a pour coordonnées (x, y+1)

**RG 010**:
>La première ligne correspond aux coordonnées du coin supérieur droit de la pelouse, 
celles du coin inférieur gauche sont supposées être (0,0).

**RG 011**:
>La suite du fichier permet de piloter toutes les tondeuses qui ont été déployées. Chaque tondeuse a deux lignes la concernant.

**RG 012**:
>La première ligne donne la position initiale de la tondeuse, ainsi que son orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres et une lettre, séparés par un espace.

**RG 013**:
>La seconde ligne est une série d'instructions ordonnant à la tondeuse d'explorer la pelouse. Les instructions sont une suite de caractères sans espaces.

**RG 014**:
>Chaque tondeuse se déplace de façon séquentielle, ce qui signifie que la seconde tondeuse ne bouge que lorsque la première a exécuté intégralement sa série d'instructions.

**RG 015**:
>Lorsqu'une tondeuse achève une série d'instruction, elle communique sa position et son orientation.
