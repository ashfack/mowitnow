# mowitnow

Ce projet répond à un exercice technique donné en entretien chez Xebia.
Ce projet s'inspire des différents projets visibles sur github de manière publique. Notamment https://github.com/gsaad/mowitnow et https://github.com/snilyes/mowitnow.

La consigne précise est :
>La société MowItNow a décidé de développer une tondeuse à gazon automatique, destinée aux
surfaces rectangulaires.<br />

>La tondeuse peut être programmée pour parcourir l'intégralité de la surface.<br />

>La position de la tondeuse est représentée par une combinaison de coordonnées (x,y) et d'une
lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S).<br />
La pelouse est
divisée en grille pour simplifier la navigation.<br />

>Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle se situe
dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.<br />

>Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres.<br />

>Les lettres possibles sont « D », « G » et « A ». <br />

>« D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche
respectivement, sans la déplacer. <br />

>« A » signifie que l'on avance la tondeuse d'une case dans la
direction à laquelle elle fait face, et sans modifier son orientation.<br />

>Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge pas,
conserve son orientation et traite la commande suivante.<br />

>On assume que la case directement au Nord de la position (x, y) a pour coordonnées (x, y+1).<br />


>Pour programmer la tondeuse, on lui fournit un fichier d'entrée construit comme suit :<br />
● La première ligne correspond aux coordonnées du coin supérieur droit de la pelouse, celles
du coin inférieur gauche sont supposées être (0,0)<br />
● La suite du fichier permet de piloter toutes les tondeuses qui ont été déployées. Chaque
tondeuse a deux lignes la concernant :<br />
● la première ligne donne la position initiale de la tondeuse, ainsi que son orientation. La
position et l'orientation sont fournies sous la forme de 2 chiffres et une lettre, séparés
par un espace<br />
● la seconde ligne est une série d'instructions ordonnant à la tondeuse d'explorer la
pelouse. Les instructions sont une suite de caractères sans espaces.<br />

>Chaque tondeuse se déplace de façon séquentielle, ce qui signifie que la seconde tondeuse ne
bouge que lorsque la première a exécuté intégralement sa série d'instructions.<br />

>Lorsqu'une tondeuse achève une série d'instruction, elle communique sa position et son
orientation.<br />


**OBJECTIF**<br />
>**Concevoir et écrire un programme s'exécutant sur une JVM et implémentant la spécification
ci-dessus et passant le test ci-après.**<br />
<pre>
TEST
Le fichier suivant est fourni en entrée :
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA
On attend le résultat suivant (position finale des tondeuses) :
1 3 N
5 1 E
NB: Les données en entrée peuvent être injectée sous une autre forme qu'un fichier (par
exemple un test automatisé)
</pre>

**Choix et interrogations pré-developpement :**<br />
* Les bornes du système : on se limite à une application JAVA avec du backend uniquement, cependant on pourrait envisager une sortie via un service web.<br />
* On pourrait également envisager une persistance en BDD, ce que nous ne ferons pas dans le cadre de cet exercice.<br />
* A première vue, pour gérer un parc de tondeuses, on pourrait penser à une solution avec un traitement parallèle auquel cas spring batch aurait été pertinent ou une utilisation avec des threads. Cependant le sujet précise que l'on ne traite qu'une tondeuse après l'autre, par conséquent nous n'opterons pas pour cette approche.<br />
* Toutes les tondeuses semblent avoir effet sur la même pelouse, on peut donc envisager d'utiliser le pattern Singleton pour cette pelouse.<br />
* Le sujet précise que pour un fichier en entrée, on attend en sortie une liste de positions finales, cependant, il fait omission de l'état de le pelouse (cellules tondues ou non). On peut donc se demander s'il faut à tout instant connaitre l'état intégral de la pelouse (donc gestion mémoire) ou s'il suffit de connaitre l'état courant de la tondeuse.<br />
* Enfin, on peut se demander s'il ne devrait pas y avoir une gestion lorsque deux tondeuses sont amenées à occuper la même position sur la pelouse ou la gestion de l'état tondu, occupé et que faire si on retond une cellule déjà tondue.<br />
* Pour finir, cette tondeuse reçoit des instructions à la française (G (gauche), D (droite), A (avancer)), il faudrait voir s'il ne faudrait pas gérér la paramétrisation de ces derniers, (L (left), R (right), F(forward)), étant donné que l'orientation est sur le système cardinal anglais. <br /> 
