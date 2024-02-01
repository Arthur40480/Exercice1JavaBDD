-- ---------------------------------------------------------------------
-- - Reconstruction de la base de données - --
-- ---------------------------------------------------------------------
DROP DATABASE IF EXISTS Shop;
CREATE DATABASE Shop;
Use Shop;

-- ---------------------------------------------------------------------
-- - Création d'un nouvel utilisateur pour la base de donnée Shop - --
-- ---------------------------------------------------------------------
-- CREATE USER 'invite'@'localhost' IDENTIFIED BY 'azertyuiop';
-- GRANT ALL PRIVILEGES ON Shop.* TO 'invite'@'localhost';
-- FLUSH PRIVILEGES;

-- ---------------------------------------------------------------------
-- - Construction de la table des articles en vente - --
-- ---------------------------------------------------------------------
CREATE TABLE T_Articles (
	IdArticle			INT(4)		PRIMARY KEY AUTO_INCREMENT,
	Description			VARCHAR(30)	NOT NULL,
	Brand				VARCHAR(30)	NOT NULL,
	UnitaryPrice		FLOAT(8)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Souris'	, 'Logitoch', 65);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Souris V2'	, 'Logitoch', 65);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Clavier'	, 'Microhard', 49.5);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Systeme d''exploitation'	, 'Fenetres Vistouille', 150);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Tapis souris'	, 'Chapeau Bleu', 5);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Cle USB 8 To'	, 'Syno', 8);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Laptop'	, 'PH', 1199);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'CD x 500'	, 'CETME', 250);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD-R x 100'	, 'CETME', 99);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'DVD+R x 100'	, 'CETME', 105);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Batterie Laptop'	, 'PH', 80);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'Casque Audio'	, 'Syno', 105);
INSERT INTO T_Articles ( Description, Brand, UnitaryPrice ) VALUES ( 'WebCam'	, 'Logitoch', 755);

-- ---------------------------------------------------------------------
-- - Construction de la table des Categories d'article - --
-- ---------------------------------------------------------------------
CREATE TABLE T_Categories (
	IdCategory 			INT(4)		PRIMARY KEY AUTO_INCREMENT,
	CatName				VARCHAR(30)	NOT NULL,
	Description			VARCHAR(100)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Categories ( CatName, Description) VALUES ( 'Périphérique'	, 'Dispositif connecté à un système de traitement de l''information central');
INSERT INTO T_Categories ( CatName, Description) VALUES ( 'Composant'	, 'Les composants font référence au matériel interne d''un PC');

-- ---------------------------------------------------------------------
-- - On ajoute à la table article un colonne appelée idCategory - --
-- - On lui ajoute une contrainte de clé étrangère à cette nouvelle colonne - --
-- - Cela signifie que cette colonne doit contenir des valeurs qui correspondent à des valeurs existantes dans une autre table. 
-- - Dans notre cas spécifique, cela signifie que la colonne IdCategory de la table T_Articles doit contenir des valeurs qui existent 
-- - déjà dans la colonne IdCategory de la table T_Categories.
-- ---------------------------------------------------------------------
ALTER TABLE T_Articles ADD COLUMN IdCategory INT(4);
ALTER TABLE T_Articles ADD FOREIGN KEY(IdCateory) REFERENCES T_Categories(IdCategory);

UPDATE T_ARTICLES SET IdCategory = 1 WHERE IdArticle = 1;
UPDATE T_ARTICLES SET IdCategory = 1 WHERE IdArticle = 2;
UPDATE T_ARTICLES SET IdCategory = 2 WHERE IdArticle = 3;
UPDATE T_ARTICLES SET IdCategory = 1 WHERE IdArticle = 4;
UPDATE T_ARTICLES SET IdCategory = 1 WHERE IdArticle = 5;
UPDATE T_ARTICLES SET IdCategory = 1 WHERE IdArticle = 6;
UPDATE T_ARTICLES SET IdCategory = 2 WHERE IdArticle = 7;
UPDATE T_ARTICLES SET IdCategory = 2 WHERE IdArticle = 8;
UPDATE T_ARTICLES SET IdCategory = 2 WHERE IdArticle = 9;
UPDATE T_ARTICLES SET IdCategory = 2 WHERE IdArticle = 10;
UPDATE T_ARTICLES SET IdCategory = 1 WHERE IdArticle = 11;
UPDATE T_ARTICLES SET IdCategory = 1 WHERE IdArticle = 12;

INSERT INTO T_Articles ( Description, Brand, UnitaryPrice, IdCategory ) VALUES ( 'Macbook', 'Apple', 2000, 1); 

-- ---------------------------------------------------------------------
-- - Construction de la table des Users - --
-- ---------------------------------------------------------------------
CREATE TABLE T_Users (
	IdUser 			INT(4)		PRIMARY KEY AUTO_INCREMENT,
	Login				VARCHAR(20)	NOT NULL,
	Password			VARCHAR(20)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Users (Login, Password) VALUES ('ArthurG', '123456789');
INSERT INTO T_Users (Login, Password) VALUES ('AmbreL', 'azertyuiop');

SELECT * FROM T_Articles;
SELECT * FROM T_Categories;
SELECT * FROM T_Users;

-- 	1.1 - Ouvrir l'invit de commande dans le bon dossier
--		- Se connecter avec la commande mysql -u root -p
--		- source Shop.sql

--	1.2 - SHOW TABLES;

--	1.3 - DESCRIBE T_Articles;

-- 	1.4 - INSERT INTO T_Articles ( Description, Brand, UnitaryPrice) VALUES ('Souris sans fil', 'Logitoch', 299);
--		- INSERT INTO T_Articles ( Description, Brand, UnitaryPrice) VALUES ('Carte Graphique', 'Nvdio', 800);

--	1.5 - UPDATE T_Articles set brand='Logitech', unitaryPrice=85 WHERE IdArticle=1;
--		- SELECT * FROM t_articles;

--	1.6 - DELETE FROM T_Articles WHERE IdArticle=14;
--		- SELECT * FROM t_articles;

--	1.7 - SELECT * FROM t_articles where unitaryPrice>100;

--	1.8 -  SELECT * FROM  T_Articles WHERE unitaryPrice>50 AND unitaryPrice<150; 

--	1.9 - SELECT * FROM T_Articles ORDER BY UnitaryPrice;

--	1.10 - SELECT Description FROM T_Articles;

--	1.11 - SELECT Description, Brand, UnitaryPrice FROM T_Articles ORDER BY Brand, UnitaryPrice; 
--			(Affiche l'ensemble des données triés par Marque, et ensuite par Prix)

--	1.12 - 
--	1.13 - SELECT idarticle, t_articles.description, brand, unitaryprice, catname from t_articles inner join t_categories;