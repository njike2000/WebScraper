FROM mariadb:10.9.4

ENV MARIADB_DATABASE=my_database
ENV MARIADB_ALLOW_EMPTY_ROOT_PASSWORD=true
ENV MYSQL_USER=your_database_user
ENV MYSQL_PASSWORD=your_database_password

COPY ./conf/* /etc/mariadb/conf.d
COPY ./init_db/* /docker-entrypoint-initdb.d/  

EXPOSE 3306