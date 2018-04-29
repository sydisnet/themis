# Created by shebert at 29/04/18
# language: fr
@HLR-Y
Fonctionnalité: Test de composants JavaEE / Jakarta EE
  Il doit être possible dans la nouvelle architecture de déployer et tester un composant serveur plus simplement
  qu'avec Arquillian.

  #noinspection CucumberUndefinedStep
  Scénario: Invoquer la méthode métier d'un composant simple automatiquement déployé dans un conteneur.
    Etant donné un composant simple
    Lorsque j'invoque un de ses méthodes métiers
    Alors le système ne me pète pas à la tronche

