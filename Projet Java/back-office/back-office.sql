-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  Dim 07 juin 2020 à 21:26
-- Version du serveur :  10.3.16-MariaDB
-- Version de PHP :  7.2.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `back-office`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `id` varchar(50) NOT NULL,
  `motDePasse` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`id`, `motDePasse`) VALUES
('root', 'root');

-- --------------------------------------------------------

--
-- Structure de la table `docetape`
--

CREATE TABLE `docetape` (
  `idEtape` int(11) NOT NULL,
  `idDoc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `docetape`
--

INSERT INTO `docetape` (`idEtape`, `idDoc`) VALUES
(1, 1),
(1224107, 2);

-- --------------------------------------------------------

--
-- Structure de la table `document`
--

CREATE TABLE `document` (
  `idDoc` int(20) NOT NULL,
  `libellé` varchar(30) NOT NULL,
  `proc` int(30) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `document`
--

INSERT INTO `document` (`idDoc`, `libellé`, `proc`, `description`) VALUES
(1, 'doc1', 1, 'Une copie de livret de famille'),
(2, 'doc2', 3, 'Certificat de residence '),
(94190102, ' doc2', 1, 'document2 '),
(264090102, ' Doc1', 2, ' carte nationale');

-- --------------------------------------------------------

--
-- Structure de la table `employeetape`
--

CREATE TABLE `employeetape` (
  `idEmploye` varchar(30) NOT NULL,
  `idEtape` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `employé`
--

CREATE TABLE `employé` (
  `id` varchar(30) NOT NULL,
  `motDePasse` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `Proc` int(11) NOT NULL,
  `Chef` tinyint(1) NOT NULL,
  `Archiver` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employé`
--

INSERT INTO `employé` (`id`, `motDePasse`, `nom`, `prenom`, `email`, `Proc`, `Chef`, `Archiver`) VALUES
('ahssaini', 'test', 'hssaini', 'abdessamad', 'gmail@', 3092107, 1, 0),
('arachidi', 'test', 'rachidi', 'achraf', 'test@', 3092107, 1, 0),
('hahmadoune', 'ANpVCDUb', 'ahmadoune', 'hamdoun', 'test@@@', 2, 0, 0),
('hhamdat', 'q9dvAqHL', 'hamdat', 'hamdoune', 'test1', 3092107, 0, 0),
('hrachidi', 'FfwtxFyt', 'rachidi', 'hamza', 'test@', 3, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `etape`
--

CREATE TABLE `etape` (
  `ID` int(11) NOT NULL,
  `Numero` int(11) NOT NULL,
  `Proc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etape`
--

INSERT INTO `etape` (`ID`, `Numero`, `Proc`) VALUES
(1, 1, 1),
(2, 1, 2),
(1224107, 1, 3),
(1747469, 1, 3747469),
(2747469, 2, 3747469);

-- --------------------------------------------------------

--
-- Structure de la table `procédure`
--

CREATE TABLE `procédure` (
  `idProcedure` int(30) NOT NULL,
  `libellé` varchar(30) NOT NULL,
  `Archive` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `procédure`
--

INSERT INTO `procédure` (`idProcedure`, `libellé`, `Archive`) VALUES
(1, 'ramed', 0),
(2, 'passport', 0),
(3, 'carte d identite', 0),
(3092107, ' Visite', 0),
(3192103, ' cpe', 0),
(3747469, ' CNSS', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`idDoc`),
  ADD KEY `fk_doc` (`proc`);

--
-- Index pour la table `employé`
--
ALTER TABLE `employé`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_employe` (`Proc`);

--
-- Index pour la table `etape`
--
ALTER TABLE `etape`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_etape` (`Proc`);

--
-- Index pour la table `procédure`
--
ALTER TABLE `procédure`
  ADD PRIMARY KEY (`idProcedure`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `fk_doc` FOREIGN KEY (`proc`) REFERENCES `procédure` (`idProcedure`);

--
-- Contraintes pour la table `employé`
--
ALTER TABLE `employé`
  ADD CONSTRAINT `fk_employe` FOREIGN KEY (`Proc`) REFERENCES `procédure` (`idProcedure`);

--
-- Contraintes pour la table `etape`
--
ALTER TABLE `etape`
  ADD CONSTRAINT `fk_etape` FOREIGN KEY (`Proc`) REFERENCES `procédure` (`idProcedure`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
