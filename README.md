# School Heroes

Ce projet a été réalisé pour une meilleure compréhension de java et des patron de conception (design patterns).

## Patron de conception

Un patron de conception fournit des solutions à des problèmes récurrents pour les développeurs.

### Patron de conception utilisée

Pour ce projet, j'ai utilisé trois patron de conception :

* Observateur
* Singleton
* Stratégie

### Observateur

L'observateur permet de notifier le changement d'un objet à un ou plusieurs objets.
Dans mon projet, j'ai utilisé le patron de conception observateur pour notifier à ma fenêtre les modifications apportées à ma liste de héros. Ainsi, lorsque j'ajoute, modifie ou supprime un héros dans ma liste, mon tableau dans ma fenêtre est mis à jour.

### Singleton

Le singleton nous permet d'avoir une seule instance d'une classe pour notre application.
J'ai utilisé le patron de conception singleton sur ma classe pour ma connexion à la base de données, ce qui garantit que je ne fais la connexion à la base de données qu'une seule fois.

### Stratégie

Le patron de conception stratégique permet de concevoir une classe ouverte à l'extension, mais fermée à la modification. C'est-à-dire une classe qui permet d'ajouter des fonctionnalités sans modifier le code source.
J'ai utilisé ce modèle de conception pour attribuer une classe à mes héros. Ma classe de héros possède une propriété 'classHero' qui me permet d'attribuer une classe à un héros. Ainsi, avec l'interface 'ActionHero' que j'ai créée, je peux ajouter une classe de héros sans modifier ma classe de héros.