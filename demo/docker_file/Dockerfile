FROM mariadb:10.9.4

ENV MARIADB_DATABASE=my_database
ENV MARIADB_ALLOW_EMPTY_ROOT_PASSWORD=true

COPY ./conf/* /etc/mariadb/conf.d
COPY ./init_db/* /docker-entrypoint-initdb.d/ 