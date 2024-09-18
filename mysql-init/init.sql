-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 18 sep. 2024 à 16:50
-- Version du serveur : 5.7.31
-- Version de PHP : 8.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `finappdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `don`
--

DROP TABLE IF EXISTS `don`;
CREATE TABLE IF NOT EXISTS `don` (
  `date` date DEFAULT NULL,
  `montant` double DEFAULT NULL,
  `montant_collecte` double DEFAULT NULL,
  `beneficiaire_id` bigint(20) DEFAULT NULL,
  `donateur_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `statut` enum('ANNULE','EN_ATTENTE','TERMINE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKixhek3rm0rywoxoan2d8buw5d` (`beneficiaire_id`),
  KEY `FK4bfs9816nxrh0lgb6gf9xe42t` (`donateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `don`
--

INSERT INTO `don` (`date`, `montant`, `montant_collecte`, `beneficiaire_id`, `donateur_id`, `id`, `description`, `statut`) VALUES
('2024-09-18', 150.75, 0, 1, NULL, 1, 'Donation for school supplies', 'EN_ATTENTE'),
('2024-09-18', 150.75, 0, 1, NULL, 2, 'Donation for school supplies', 'EN_ATTENTE'),
('2024-09-18', 150.75, 0, 1, NULL, 3, 'Donation for school supplies', 'EN_ATTENTE'),
('2024-09-18', 150.75, 0, 1, NULL, 4, 'Donation for school supplies', 'EN_ATTENTE'),
('2024-09-18', 20000, 22430, 1, 1, 5, 'Donation for school supplies', 'TERMINE');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `solde` double DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `role` enum('BENEFICIAIRE','DONATEUR') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`solde`, `id`, `email`, `mot_de_passe`, `nom`, `role`) VALUES
(0, 1, 'albertlionelmalang@gmail.com', '$2a$10$1zFWFM9JHsxCEExwvCA5SuEXlo1G54kPURuyOak7JbVEmW.EOj9.y', 'MALANG Albert Lionel', 'DONATEUR');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `don`
--
ALTER TABLE `don`
  ADD CONSTRAINT `FK4bfs9816nxrh0lgb6gf9xe42t` FOREIGN KEY (`donateur_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FKixhek3rm0rywoxoan2d8buw5d` FOREIGN KEY (`beneficiaire_id`) REFERENCES `utilisateur` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
