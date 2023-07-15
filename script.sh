docker run --name mysql-test -e MYSQL_ROOT_PASSWORD=12 -e MYSQL_DATABASE=springPractice -d -p 3326:3306 mysql
docker run --name mysql-develop -e MYSQL_ROOT_PASSWORD=12 -e MYSQL_DATABASE=springPractice -d -p 3316:3306 mysql
