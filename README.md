# parkin-nantes


# Contexte
Les habitants de la ville de Nantes ont cotisé à une campagne de crowdfunding pour
financer une application mobile Android.
Cette application devra utiliser les API Open Data mises à disposition par Nantes Métropole
pour lister les parkings de la ville de Nantes (capacité, moyens de paiement...) et afficher en
temps réel les places disponibles.
# Ressources
Vous devrez utiliser les deux API REST suivantes au format qui vous semble le plus
approprié.
● Liste et description des parkings de la ville de Nantes :
https://data.nantes.fr/donnees/detail/offres-de-services-des-parkings-publics-de-la-v
ille-de-nantes-1/
● Service de disponibilité des places de parking en temps réel :
https://data.nantes.fr/donnees/fonctionnement-de-lapi/getdisponibiliteparkingspub
lics/
Vous êtes libres d’évaluer d’autres sources de données qui pourraient ajouter des
informations utiles pour l’utilisateur, mais ces deux premières sources sont obligatoires.
2
# Cahier des charges
Menu principal
La page d’accueil devra permettre d’accéder à l’ensemble des fonctionnalités de
l’application. Nous vous laissons définir l’ergonomie la plus adaptée.
Liste des parkings
Cet écran devra lister l’ensemble des parkings de la ville de Nantes, avec à minima les
informations suivantes :
● Nom du parking
● Adresse
● Icône indiquant les moyens de paiement disponibles (e.g. CB, espèce…)
● Nombre de place disponibles
● Capacité totale
● Une icône indiquant si le parking a été mis en favoris
Au clic sur un des items de la liste, l’utilisateur sera redirigé vers un écran détaillant le
parking correspondant.
Cette liste pourra être filtrée en temps réel via un champ de recherche située en haut de
l’écran.
Carte des parkings
Cet écran présente les mêmes informations que la liste à l’aide d’une Google Map. Au clic
sur un marqueur, l’InfoWindow devra présenter le nom du parking, son adresse et le
nombre de places disponibles.
Au clic sur l’InfoWindow, l’utilisateur sera redirigé vers un écran détaillant le parking
correspondant. Cette carte pourra être filtrée en temps réel via un champ de recherche
située en haut de l’écran.
Détail d’un parking
Cet écran apporte des informations détaillées sur un parking donné.
Vous devrez intégrer :
● Un image Google Street View correspondant aux coordonées du parking (cf
https://developers.google.com/maps/documentation/streetview/ )
● Un bouton permettant d’ajouter/retirer le parking des favoris
● L’ensemble des informations obtenues grâce aux APIs REST.
3
Recherche
Cet écran permet de lancer une recherche des parkings de Nantes en fonction :
● De leur adresse (champ texte)
● De leur nom (champ texte)
● Des moyens de paiement acceptés (checkbox pour chacun des moyens de
paiement)
● Du nombre de places disponibles (slider)
Au clic sur le bouton de validation, une recherche est lancée et les résultats sont affichés
sur l’écran de liste.
Favoris
Cet écran reprend exactement le même comportement que l’écran de liste mais n’affiche
que les parkings mis en favoris par l’utilisateur.
Notifications sonores
L’utilisateur aura la possibilité d’activer/désactiver les notifications sonores. Si activées,
l’application envoie une notification locale dès qu’un parking avec des places libre est
détecté à moins de 500m. L’application devra bien sûr effectuer ce test fréquemment et
lorsque l’utilisateur bouge, tout en portant attention à la consommation de batterie.
