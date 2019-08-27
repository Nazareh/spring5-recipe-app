CREATE DATABASE dev;
CREATE DATABASE prod;

CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'dev_user'@'%' IDENTIFIED BY 'password';
CREATE USER 'prod_user'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'prod_user'@'%' IDENTIFIED BY 'password';


GRANT SELECT,INSERT,UPDATE,DELETE ON dev.* TO 'dev_user'@'localhost';
GRANT SELECT,INSERT,UPDATE,DELETE ON dev.* TO 'dev_user'@'%';
GRANT SELECT,INSERT,UPDATE,DELETE ON prod.* TO 'prod_user'@'localhost';
GRANT SELECT,INSERT,UPDATE,DELETE ON prod.* TO 'prod_user'@'%';

