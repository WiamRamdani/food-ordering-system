-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 30 mars 2025 à 01:50
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `food_ordering_system`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id_admin` bigint(20) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `role` tinyint(4) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `admin_seq`
--

CREATE TABLE `admin_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin_seq`
--

INSERT INTO `admin_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `id_adresse` bigint(20) NOT NULL,
  `id_utilisateur` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `adresse_seq`
--

CREATE TABLE `adresse_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `adresse_seq`
--

INSERT INTO `adresse_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `avis`
--

CREATE TABLE `avis` (
  `id_avis` bigint(20) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `note` float NOT NULL,
  `id_utilisateur` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `avis_seq`
--

CREATE TABLE `avis_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `avis_seq`
--

INSERT INTO `avis_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

CREATE TABLE `commandes` (
  `id_commande` bigint(20) NOT NULL,
  `créée_a` datetime(6) DEFAULT NULL,
  `heure_estimée` datetime(6) DEFAULT NULL,
  `heure_réelle` datetime(6) DEFAULT NULL,
  `id_plat` bigint(20) NOT NULL,
  `montant_total` bigint(20) NOT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `adresse_livraison_id_adresse` bigint(20) DEFAULT NULL,
  `client_id_utilisateur` bigint(20) DEFAULT NULL,
  `livreur_id_livreur` bigint(20) DEFAULT NULL,
  `restaurant_id_restaurant` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commandes_plats`
--

CREATE TABLE `commandes_plats` (
  `commande_id_commande` bigint(20) NOT NULL,
  `plats_id_plat` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commandes_seq`
--

CREATE TABLE `commandes_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `commandes_seq`
--

INSERT INTO `commandes_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `ingredients`
--

CREATE TABLE `ingredients` (
  `id_ingredient` bigint(20) NOT NULL,
  `plat_id_plat` bigint(20) DEFAULT NULL,
  `id_plat` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `ingredients_seq`
--

CREATE TABLE `ingredients_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ingredients_seq`
--

INSERT INTO `ingredients_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `livreur`
--

CREATE TABLE `livreur` (
  `id_livreur` bigint(20) NOT NULL,
  `disponible` bit(1) NOT NULL,
  `matricule_vehicule` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `restaurant_id_restaurant` bigint(20) DEFAULT NULL,
  `id_restaurant` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `livreur_commandes`
--

CREATE TABLE `livreur_commandes` (
  `livreur_id_livreur` bigint(20) NOT NULL,
  `commandes_id_commande` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `livreur_seq`
--

CREATE TABLE `livreur_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `livreur_seq`
--

INSERT INTO `livreur_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `menu`
--

CREATE TABLE `menu` (
  `id_menu` bigint(20) NOT NULL,
  `restaurant_id_restaurant` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `menu_seq`
--

CREATE TABLE `menu_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `menu_seq`
--

INSERT INTO `menu_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `plats`
--

CREATE TABLE `plats` (
  `id_plat` bigint(20) NOT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `disponible` bit(1) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `note` float NOT NULL,
  `prix` double NOT NULL,
  `quantite` int(11) NOT NULL,
  `id_menu` bigint(20) DEFAULT NULL,
  `restaurant_id_restaurant` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `plats_image`
--

CREATE TABLE `plats_image` (
  `plats_id_plat` bigint(20) NOT NULL,
  `image` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `plats_ingredients`
--

CREATE TABLE `plats_ingredients` (
  `plats_id_plat` bigint(20) NOT NULL,
  `ingredients_id_ingredient` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `plats_seq`
--

CREATE TABLE `plats_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `plats_seq`
--

INSERT INTO `plats_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `restaurants`
--

CREATE TABLE `restaurants` (
  `id_restaurant` bigint(20) NOT NULL,
  `type_cuisine` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `facebook` varchar(255) DEFAULT NULL,
  `instagram` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `heure_fermeture` datetime(6) DEFAULT NULL,
  `heure_ouverture` datetime(6) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `note` float NOT NULL,
  `ouvert` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `adresse_id_adresse` bigint(20) DEFAULT NULL,
  `role` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `restaurants_commandes`
--

CREATE TABLE `restaurants_commandes` (
  `restaurants_id_restaurant` bigint(20) NOT NULL,
  `commandes_id_commande` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `restaurants_images`
--

CREATE TABLE `restaurants_images` (
  `restaurants_id_restaurant` bigint(20) NOT NULL,
  `images` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `restaurants_menus`
--

CREATE TABLE `restaurants_menus` (
  `restaurants_id_restaurant` bigint(20) NOT NULL,
  `menus_id_menu` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `restaurants_plats`
--

CREATE TABLE `restaurants_plats` (
  `restaurants_id_restaurant` bigint(20) NOT NULL,
  `plats_id_plat` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `restaurants_seq`
--

CREATE TABLE `restaurants_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `restaurants_seq`
--

INSERT INTO `restaurants_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id_utilisateur` bigint(20) NOT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `login` varchar(25) DEFAULT NULL,
  `nom` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `prenom` varchar(25) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user_restaurants`
--

CREATE TABLE `user_restaurants` (
  `id_utilisateur` bigint(20) NOT NULL,
  `id_restaurant` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` bigint(20) NOT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `login` varchar(25) DEFAULT NULL,
  `nom` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `prenom` varchar(25) DEFAULT NULL,
  `role` tinyint(4) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`id_adresse`),
  ADD KEY `FKrk0fnck5wu3u679s36nqcstan` (`id_utilisateur`);

--
-- Index pour la table `avis`
--
ALTER TABLE `avis`
  ADD PRIMARY KEY (`id_avis`),
  ADD KEY `FKf881mvur1o8kp834d15fgb2i3` (`id_utilisateur`);

--
-- Index pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`id_commande`),
  ADD KEY `FKj405carhojlfnr7g6rvr7eig6` (`adresse_livraison_id_adresse`),
  ADD KEY `FK3u930k0elpsualrssydq9mdei` (`livreur_id_livreur`),
  ADD KEY `FKbjfbcr4fabae9lm2qgru8oe0y` (`restaurant_id_restaurant`),
  ADD KEY `FK9kfwh9dq0j3mfhuxb41omq2r7` (`client_id_utilisateur`);

--
-- Index pour la table `commandes_plats`
--
ALTER TABLE `commandes_plats`
  ADD UNIQUE KEY `UK_3en24gb35aycmyg11qd4j3oin` (`plats_id_plat`),
  ADD KEY `FK3jnmamr0nk9u2plke36oirea8` (`commande_id_commande`);

--
-- Index pour la table `ingredients`
--
ALTER TABLE `ingredients`
  ADD PRIMARY KEY (`id_ingredient`),
  ADD KEY `FKkl4l3gusrvffqa4leb4m67k43` (`plat_id_plat`),
  ADD KEY `FK3xbx05w3f790g0c4pqhh4y2ow` (`id_plat`);

--
-- Index pour la table `livreur`
--
ALTER TABLE `livreur`
  ADD PRIMARY KEY (`id_livreur`),
  ADD UNIQUE KEY `UK_4q29del8mf5mo538d8dyfjs9v` (`restaurant_id_restaurant`),
  ADD UNIQUE KEY `UK_c2qj5lnwaa1x4nys8nalu263a` (`id_restaurant`);

--
-- Index pour la table `livreur_commandes`
--
ALTER TABLE `livreur_commandes`
  ADD UNIQUE KEY `UK_r1v0epwsnf108klbbkhs927av` (`commandes_id_commande`),
  ADD KEY `FKq9joakikcq35wh8gmrkf23wtb` (`livreur_id_livreur`);

--
-- Index pour la table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_menu`),
  ADD KEY `FKe6jnem93ouvxicu26jq8grxrq` (`restaurant_id_restaurant`);

--
-- Index pour la table `plats`
--
ALTER TABLE `plats`
  ADD PRIMARY KEY (`id_plat`),
  ADD KEY `FKriiq4qg8d3i3xtvw80b7j2x61` (`id_menu`),
  ADD KEY `FK8siqj2vkko7c012s9a11n2mos` (`restaurant_id_restaurant`);

--
-- Index pour la table `plats_image`
--
ALTER TABLE `plats_image`
  ADD KEY `FKgecdkx6mi7kbxk9eriqybrycg` (`plats_id_plat`);

--
-- Index pour la table `plats_ingredients`
--
ALTER TABLE `plats_ingredients`
  ADD KEY `FK93p14gng7f6vg67mep7u12k1m` (`ingredients_id_ingredient`),
  ADD KEY `FKr7koorudxq8ajhyknywp417dh` (`plats_id_plat`);

--
-- Index pour la table `restaurants`
--
ALTER TABLE `restaurants`
  ADD PRIMARY KEY (`id_restaurant`),
  ADD UNIQUE KEY `UK_62l7u6xciissckxqjhai4g3u7` (`adresse_id_adresse`);

--
-- Index pour la table `restaurants_commandes`
--
ALTER TABLE `restaurants_commandes`
  ADD UNIQUE KEY `UK_pm9ftn2j605ir423pv17u8uf0` (`commandes_id_commande`),
  ADD KEY `FKn7pan8hd9inj3jis2rcq2ot97` (`restaurants_id_restaurant`);

--
-- Index pour la table `restaurants_images`
--
ALTER TABLE `restaurants_images`
  ADD KEY `FKd0w8w74adsdfqm6g9i5tr2pmu` (`restaurants_id_restaurant`);

--
-- Index pour la table `restaurants_menus`
--
ALTER TABLE `restaurants_menus`
  ADD UNIQUE KEY `UK_7jpxpbogijed2i2h9btgo28d9` (`menus_id_menu`),
  ADD KEY `FKarwfbskr3f13q5x86n49f2qhx` (`restaurants_id_restaurant`);

--
-- Index pour la table `restaurants_plats`
--
ALTER TABLE `restaurants_plats`
  ADD UNIQUE KEY `UK_r4g1wnynn7kmlxkn47ss0ruqr` (`plats_id_plat`),
  ADD KEY `FKl4v1236ov5cr9dwgwd9gjek9` (`restaurants_id_restaurant`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_utilisateur`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Index pour la table `user_restaurants`
--
ALTER TABLE `user_restaurants`
  ADD UNIQUE KEY `UK_694frfwu2mvabmwv1e5iktknh` (`id_restaurant`),
  ADD KEY `FKiy045ddeei4rjbs7fl4o0nbnx` (`id_utilisateur`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id_utilisateur` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `FK9p6ho7omjmvlt2vqhbmt2i5u0` FOREIGN KEY (`id_utilisateur`) REFERENCES `users` (`id_utilisateur`),
  ADD CONSTRAINT `FKrk0fnck5wu3u679s36nqcstan` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `avis`
--
ALTER TABLE `avis`
  ADD CONSTRAINT `FKe3vj354a4xt0nm1aw5ftpcae` FOREIGN KEY (`id_utilisateur`) REFERENCES `users` (`id_utilisateur`),
  ADD CONSTRAINT `FKf881mvur1o8kp834d15fgb2i3` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `FK3u930k0elpsualrssydq9mdei` FOREIGN KEY (`livreur_id_livreur`) REFERENCES `livreur` (`id_livreur`),
  ADD CONSTRAINT `FK6g00dqi715ivh9pkadyna2wwr` FOREIGN KEY (`client_id_utilisateur`) REFERENCES `users` (`id_utilisateur`),
  ADD CONSTRAINT `FK9kfwh9dq0j3mfhuxb41omq2r7` FOREIGN KEY (`client_id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`),
  ADD CONSTRAINT `FKbjfbcr4fabae9lm2qgru8oe0y` FOREIGN KEY (`restaurant_id_restaurant`) REFERENCES `restaurants` (`id_restaurant`),
  ADD CONSTRAINT `FKj405carhojlfnr7g6rvr7eig6` FOREIGN KEY (`adresse_livraison_id_adresse`) REFERENCES `adresse` (`id_adresse`);

--
-- Contraintes pour la table `commandes_plats`
--
ALTER TABLE `commandes_plats`
  ADD CONSTRAINT `FK3jnmamr0nk9u2plke36oirea8` FOREIGN KEY (`commande_id_commande`) REFERENCES `commandes` (`id_commande`),
  ADD CONSTRAINT `FKgdmas1r8kgmwpba4bw7kkk5vs` FOREIGN KEY (`plats_id_plat`) REFERENCES `plats` (`id_plat`);

--
-- Contraintes pour la table `ingredients`
--
ALTER TABLE `ingredients`
  ADD CONSTRAINT `FK3xbx05w3f790g0c4pqhh4y2ow` FOREIGN KEY (`id_plat`) REFERENCES `plats` (`id_plat`),
  ADD CONSTRAINT `FKkl4l3gusrvffqa4leb4m67k43` FOREIGN KEY (`plat_id_plat`) REFERENCES `plats` (`id_plat`);

--
-- Contraintes pour la table `livreur`
--
ALTER TABLE `livreur`
  ADD CONSTRAINT `FKd21p4epdjfxkrwsiu8lx8aj0e` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`),
  ADD CONSTRAINT `FKs92jd6qdax2b8d2tqhbc5p90q` FOREIGN KEY (`restaurant_id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

--
-- Contraintes pour la table `livreur_commandes`
--
ALTER TABLE `livreur_commandes`
  ADD CONSTRAINT `FKq9joakikcq35wh8gmrkf23wtb` FOREIGN KEY (`livreur_id_livreur`) REFERENCES `livreur` (`id_livreur`),
  ADD CONSTRAINT `FKt5939n8ejynhh9mdvagjncgp3` FOREIGN KEY (`commandes_id_commande`) REFERENCES `commandes` (`id_commande`);

--
-- Contraintes pour la table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `FKe6jnem93ouvxicu26jq8grxrq` FOREIGN KEY (`restaurant_id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

--
-- Contraintes pour la table `plats`
--
ALTER TABLE `plats`
  ADD CONSTRAINT `FK8siqj2vkko7c012s9a11n2mos` FOREIGN KEY (`restaurant_id_restaurant`) REFERENCES `restaurants` (`id_restaurant`),
  ADD CONSTRAINT `FKriiq4qg8d3i3xtvw80b7j2x61` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`);

--
-- Contraintes pour la table `plats_image`
--
ALTER TABLE `plats_image`
  ADD CONSTRAINT `FKgecdkx6mi7kbxk9eriqybrycg` FOREIGN KEY (`plats_id_plat`) REFERENCES `plats` (`id_plat`);

--
-- Contraintes pour la table `plats_ingredients`
--
ALTER TABLE `plats_ingredients`
  ADD CONSTRAINT `FK93p14gng7f6vg67mep7u12k1m` FOREIGN KEY (`ingredients_id_ingredient`) REFERENCES `ingredients` (`id_ingredient`),
  ADD CONSTRAINT `FKr7koorudxq8ajhyknywp417dh` FOREIGN KEY (`plats_id_plat`) REFERENCES `plats` (`id_plat`);

--
-- Contraintes pour la table `restaurants`
--
ALTER TABLE `restaurants`
  ADD CONSTRAINT `FKd5r93elnwenu8b22iyn7wpagi` FOREIGN KEY (`adresse_id_adresse`) REFERENCES `adresse` (`id_adresse`);

--
-- Contraintes pour la table `restaurants_commandes`
--
ALTER TABLE `restaurants_commandes`
  ADD CONSTRAINT `FKn7pan8hd9inj3jis2rcq2ot97` FOREIGN KEY (`restaurants_id_restaurant`) REFERENCES `restaurants` (`id_restaurant`),
  ADD CONSTRAINT `FKqlv1aujduxr8ih1jhiuf2qkt2` FOREIGN KEY (`commandes_id_commande`) REFERENCES `commandes` (`id_commande`);

--
-- Contraintes pour la table `restaurants_images`
--
ALTER TABLE `restaurants_images`
  ADD CONSTRAINT `FKd0w8w74adsdfqm6g9i5tr2pmu` FOREIGN KEY (`restaurants_id_restaurant`) REFERENCES `restaurants` (`id_restaurant`);

--
-- Contraintes pour la table `restaurants_menus`
--
ALTER TABLE `restaurants_menus`
  ADD CONSTRAINT `FKarwfbskr3f13q5x86n49f2qhx` FOREIGN KEY (`restaurants_id_restaurant`) REFERENCES `restaurants` (`id_restaurant`),
  ADD CONSTRAINT `FKtfe8f9jpc1dm4u8hddsac7kan` FOREIGN KEY (`menus_id_menu`) REFERENCES `menu` (`id_menu`);

--
-- Contraintes pour la table `restaurants_plats`
--
ALTER TABLE `restaurants_plats`
  ADD CONSTRAINT `FKl4v1236ov5cr9dwgwd9gjek9` FOREIGN KEY (`restaurants_id_restaurant`) REFERENCES `restaurants` (`id_restaurant`),
  ADD CONSTRAINT `FKpva7a1agjwa6ul868hi1eescv` FOREIGN KEY (`plats_id_plat`) REFERENCES `plats` (`id_plat`);

--
-- Contraintes pour la table `user_restaurants`
--
ALTER TABLE `user_restaurants`
  ADD CONSTRAINT `FKbyi8f6x7x1bl0lfeyla1f5s7m` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`),
  ADD CONSTRAINT `FKiy045ddeei4rjbs7fl4o0nbnx` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`),
  ADD CONSTRAINT `FKm6130r69tvodjpcrm8ep2ux0y` FOREIGN KEY (`id_utilisateur`) REFERENCES `users` (`id_utilisateur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
