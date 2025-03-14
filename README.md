RDV Médical

RDV Médical est une application web développée pour faciliter la prise de rendez-vous médicaux entre patients et médecins. Elle offre une interface utilisateur intuitive pour les patients, les médecins et les administrateurs, avec des fonctionnalités telles que la gestion des rendez-vous, la confirmation ou l’annulation, et une administration des utilisateurs. Ce projet a été réalisé par un groupe dans le cadre d’un développement collaboratif, combinant un backend Spring Boot et un frontend React.
Fonctionnalités

    Patients : Prise de rendez-vous avec choix de médecin et créneau horaire, visualisation et annulation des rendez-vous.
    Médecins : Gestion des rendez-vous (confirmation ou consultation des demandes).
    Administrateurs : Supervision des utilisateurs avec filtrage par rôle.
    Interface : Design moderne avec animations fluides (React Spring) et icônes (React Icons), optimisé pour une expérience utilisateur agréable.

Prérequis

Pour cloner et exécuter ce projet sur votre machine, assurez-vous d’avoir les outils suivants installés :

    Backend :
        Java 17 (OpenJDK recommandé)
        Maven 3.9.x ou supérieur
        MySQL 8.0 (ou autre base de données compatible JPA)
    Frontend :
        Node.js 18.x ou supérieur
        npm 9.x ou supérieur
    Outils supplémentaires :
        Git
        Un IDE (ex. IntelliJ IDEA, VSCode)

Installation

Suivez ces étapes pour cloner et configurer le projet sur votre ordinateur.
1. Cloner le dépôt
bash
git clone https://github.com/<votre-utilisateur>/rdv-medical.git
cd rdv-medical
2. Configurer le Backend

    Naviguer dans le répertoire backend :
    bash

cd rdv_medical
Configurer la base de données :

    Créez une base de données MySQL nommée rdv_medical_db :
    sql

CREATE DATABASE rdv_medical_db;
Mettez à jour src/main/resources/application.properties avec vos identifiants MySQL :
properties

    spring.datasource.url=jdbc:mysql://localhost:3306/rdv_medical_db
    spring.datasource.username=votre_utilisateur
    spring.datasource.password=votre_mot_de_passe
    spring.jpa.hibernate.ddl-auto=update

Compiler et lancer le backend :
bash

    mvn clean package
    java -jar target/rdv_medical-0.0.1-SNAPSHOT.jar
        Le serveur démarre sur http://localhost:8080.

3. Configurer le Frontend

    Naviguer dans le répertoire frontend :
    bash

cd ../rdv-medical-frontend
Installer les dépendances :
bash
npm install
Lancer le frontend :
bash

    npm run dev
        L’application est accessible sur http://localhost:5173.

4. Vérifier le fonctionnement

    Ouvrez votre navigateur à http://localhost:5173.
    Connectez-vous avec des identifiants de test (à créer via le backend si nécessaire).

Structure du projet

    /rdv_medical/ : Backend Spring Boot
        src/main/java : Code source Java (controllers, services, entités).
        src/main/resources : Configuration (ex. application.properties).
    /rdv-medical-frontend/ : Frontend React
        src/components : Composants React (Login, Dashboards, etc.).
        src/styles.css : Styles globaux avec animations CSS.

Utilisation

    Connexion :
        Accédez à http://localhost:5173 et utilisez des identifiants (patient, médecin, admin) définis dans la base de données.
    Patients :
        Prenez un rendez-vous via "Prendre RDV" et consultez vos rendez-vous dans le dashboard.
    Médecins :
        Confirmez ou consultez les rendez-vous dans votre dashboard.
    Administrateurs :
        Gérez les utilisateurs via le dashboard admin.

Dépendances principales

    Backend :
        Spring Boot 3.2.4
        Spring Data JPA
        MySQL Connector
        JJWT (JSON Web Tokens)
    Frontend :
        React 18.3.1
        React Router DOM
        Axios
        React Icons
        React Spring
        Bootstrap 5.3.3

Consultez pom.xml (backend) et package.json (frontend) pour la liste complète.
Compilation et déploiement
Backend
bash
cd rdv_medical
mvn clean package
java -jar target/rdv_medical-0.0.1-SNAPSHOT.jar
Frontend
bash
cd rdv-medical-frontend
npm run build

    Les fichiers statiques sont générés dans dist/, prêts à être déployés (ex. sur Netlify).

Résolution des problèmes

    Erreur Maven "release version 21 not supported" :
        Assurez-vous d’utiliser Java 17 :
        bash

        export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
        Vérifiez pom.xml pour <java.version>17</java.version>.
    Page blanche frontend :
        Vérifiez la console du navigateur (F12) pour les erreurs.
        Assurez-vous que le backend est en marche (http://localhost:8080).
    Problèmes de connexion API :
        Confirmez que les URLs dans les composants (ex. axios.get('http://localhost:8080/api/...')) correspondent à votre backend.

Contributions

Les contributions sont les bienvenues ! Pour proposer des améliorations :

    Forkez le dépôt.
    Créez une branche (git checkout -b feature/amélioration).
    Soumettez une pull request.

Licence

Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails (à créer si nécessaire).

Tu peux copier ce texte directement dans un fichier nommé README.md à la racine de ton dépôt (/rdv-medical/README.md), puis le pousser sur GitHub :
bash
git add README.md
git commit -m "Ajout du README pour le projet"
git push origin main

Ce README est prêt à l’emploi. Maintenant, revenons à ton problème d’affichage : partage-moi ce que tu vois dans la console du navigateur ou après avoir testé avec le App.jsx minimal que je t’ai proposé ("Bonjour, ça marche !"). On va faire apparaître ton interface !
