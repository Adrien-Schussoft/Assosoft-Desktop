CREATE TABLE Association(
   Num_Asso INT AUTO_INCREMENT PRIMARY KEY,
   Nom VARCHAR(50) UNIQUE KEY,
   Rue VARCHAR(50),
   Ville VARCHAR(50),
   Code_Postal INT,
   Logo VARCHAR(50),
   Actif BOOLEAN
);

CREATE TABLE Users(
   Num_User INT AUTO_INCREMENT PRIMARY KEY,
   Nom VARCHAR(50),
   Prenom VARCHAR(50),
   Rue VARCHAR(50),
   Ville VARCHAR(50),
   Code_Postal INT,
   Mail VARCHAR(50),
   Date_Inscription DATE,
   Password VARCHAR(50),
   Actif BOOLEAN,
   Root BOOLEAN,
   Contact VARCHAR(50)
);

CREATE TABLE Donateur(
   Num_Donateur INT AUTO_INCREMENT PRIMARY KEY,
   Nom VARCHAR(50),
   Prenom VARCHAR(50),
   Rue VARCHAR(50),
   Ville VARCHAR(50),
   Code_Postal INT,
   Mail VARCHAR(50),
   Date_Inscription DATE,
   Password VARCHAR(50)
);

CREATE TABLE Service(
   Num_Service INT AUTO_INCREMENT PRIMARY KEY,
   Nom VARCHAR(50),
   Num_Asso INT NOT NULL REFERENCES Association(Num_Asso)
);

CREATE TABLE Don(
   Num_Don INT AUTO_INCREMENT PRIMARY KEY,
   Type VARCHAR(50),
   Etat BOOLEAN,
   Montant FLOAT(10),
   Contenu VARCHAR(50),
   Date_Don DATE,
   Num_Donateur INT NOT NULL REFERENCES Donateur(Num_Donateur),
   Num_User INT NOT NULL REFERENCES Users(Num_User)
);

CREATE TABLE Activite(
   Num_Activite INT AUTO_INCREMENT PRIMARY KEY,
   Nom VARCHAR(50),
   Date_Debut DATE,
   Date_Fin DATE,
   Budget FLOAT(10),
   Cout FLOAT(10),
   Convocation BOOLEAN,
   Compte_rendu VARCHAR(50),
   Num_Asso INT NOT NULL REFERENCES Association(Num_Asso)
);

CREATE TABLE Present_Activite(
   Num_Activite INT NOT NULL REFERENCES Activite(Num_Activite),
   Num_User INT NOT NULL REFERENCES Users(Num_User),
   PRIMARY KEY(Num_Activite, Num_User)
);

CREATE TABLE Appartient_Activite(
   Num_Activite INT NOT NULL REFERENCES Activite(Num_Activite),
   Num_User INT NOT NULL REFERENCES Users(Num_User),
   PRIMARY KEY(Num_Activite, Num_User)
);

CREATE TABLE Appartient_Asso(
   Num_Asso INT NOT NULL REFERENCES Association(Num_Asso),
   Num_User INT NOT NULL REFERENCES Users(Num_User),
   PRIMARY KEY(Num_Asso, Num_User)
);

CREATE TABLE Recoit(
   Num_Asso INT NOT NULL REFERENCES Association(Num_Asso),
   Num_Don INT NOT NULL REFERENCES Don(Num_Don),
   PRIMARY KEY(Num_Asso, Num_Don)
);
