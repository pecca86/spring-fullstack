## FULLSTACK NOTES

# DOCKER
- Run a new postgres instance on through a local network
    1. Create folder for storing db
    2. Create docker network: docker network create <network_name>
    3. Spin up the db on the network:
        docker run --name <instance_name> -p 5432:5432 --network=<network_name> -v "$PWD:/var/lib/postgresql/data" -e POSTGRES_PASSWORD=password -d postgres:<tag>
        (v = variables, e = env variables, d = run in detached mode)
    4. Connect to the database through another container:
        - docker run -it --rm --network=<network_name> postgres:<tag> psql -h <container_name> -U postgres
        (it = interactive, rm = kills the container when quitting, h = host, U = user)
- List running instances: docker ps
- Stop container: docker stop <id> / <name>


# MAVEN


# DOCKER & JIB


# AWS


# SPRING DATA JPA