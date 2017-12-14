-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 14 Décembre 2017 à 15:35
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `garderie`
--

-- --------------------------------------------------------

--
-- Structure de la table `enfant`
--

CREATE TABLE IF NOT EXISTS `enfant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `responsable_legal_id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateNaissance` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_34B70CA246135043` (`responsable_legal_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Contenu de la table `enfant`
--

INSERT INTO `enfant` (`id`, `responsable_legal_id`, `nom`, `prenom`, `dateNaissance`) VALUES
(1, 2, 'DUPONT', 'Jean', ''),
(2, 2, 'DUPONT', 'Jeanna', ''),
(3, 3, 'ESSEUL', 'Test', ''),
(4, 3, 'ESSEUL', 'Toto', '');

-- --------------------------------------------------------

--
-- Structure de la table `jour`
--

CREATE TABLE IF NOT EXISTS `jour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelleJour` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Contenu de la table `jour`
--

INSERT INTO `jour` (`id`, `libelleJour`) VALUES
(1, 'Lundi');

-- --------------------------------------------------------

--
-- Structure de la table `presence`
--

CREATE TABLE IF NOT EXISTS `presence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enfant_id` int(11) NOT NULL,
  `jour_id` int(11) NOT NULL,
  `heureArriveeMatin` time NOT NULL,
  `heureDepartSoir` time NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_6977C7A5450D2529` (`enfant_id`),
  KEY `IDX_6977C7A5220C6AD0` (`jour_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Contenu de la table `presence`
--

INSERT INTO `presence` (`id`, `enfant_id`, `jour_id`, `heureArriveeMatin`, `heureDepartSoir`, `date`) VALUES
(2, 1, 1, '08:00:00', '19:00:00', '2017-04-04'),
(4, 1, 1, '08:00:00', '18:00:00', '0000-00-00');

-- --------------------------------------------------------

--
-- Structure de la table `responsable_legal`
--

CREATE TABLE IF NOT EXISTS `responsable_legal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tarif_id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `telephone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `QF` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_82F3E532357C0A59` (`tarif_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Contenu de la table `responsable_legal`
--

INSERT INTO `responsable_legal` (`id`, `tarif_id`, `nom`, `prenom`, `telephone`, `QF`) VALUES
(2, 1, 'DUPONT', 'Maxime', '', ''),
(3, 2, 'ESSEUL', 'Steven', '', '');

-- --------------------------------------------------------

--
-- Structure de la table `tarif`
--

CREATE TABLE IF NOT EXISTS `tarif` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prixQuartHeure` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Contenu de la table `tarif`
--

INSERT INTO `tarif` (`id`, `prixQuartHeure`) VALUES
(1, '0.28'),
(2, '0.33');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `enfant`
--
ALTER TABLE `enfant`
  ADD CONSTRAINT `FK_34B70CA246135043` FOREIGN KEY (`responsable_legal_id`) REFERENCES `responsable_legal` (`id`);

--
-- Contraintes pour la table `presence`
--
ALTER TABLE `presence`
  ADD CONSTRAINT `FK_6977C7A5220C6AD0` FOREIGN KEY (`jour_id`) REFERENCES `jour` (`id`),
  ADD CONSTRAINT `FK_6977C7A5450D2529` FOREIGN KEY (`enfant_id`) REFERENCES `enfant` (`id`);

--
-- Contraintes pour la table `responsable_legal`
--
ALTER TABLE `responsable_legal`
  ADD CONSTRAINT `FK_82F3E532357C0A59` FOREIGN KEY (`tarif_id`) REFERENCES `tarif` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
