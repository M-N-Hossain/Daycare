USE daycare;
CREATE TABLE parent_List(
    parent_ID INT AUTO_INCREMENT PRIMARY KEY,
    father_Name VARCHAR(255) NULL,
    mother_Name VARCHAR(255) NULL ,
    address VARCHAR(255) NOT NULL ,
    contact_Nr VARCHAR(255) NOT NULL
);
CREATE TABLE waiting_List(
    waiting_ID INT AUTO_INCREMENT PRIMARY KEY ,
    first_Name VARCHAR(255) NOT NULL ,
    last_Name VARCHAR(255) NOT NULL ,
    birth_Day DATE NOT NULL,
    parent_ID INT ,
    FOREIGN KEY (parent_ID) REFERENCES parent_List (parent_ID) ON DELETE CASCADE
);

CREATE TABLE admit_Children_List(
    id INT AUTO_INCREMENT PRIMARY KEY ,
    first_Name VARCHAR(255) NOT NULL,
    last_Name VARCHAR(255) NOT NULL ,
    birth_Day DATE NOT NULL ,
    parent_ID INT,
    FOREIGN KEY (parent_ID) REFERENCES parent_List(parent_ID) ON DELETE CASCADE
);

DROP TABLE waiting_list;
DROP TABLE admit_children_list;
DROP TABLE  parent_list;