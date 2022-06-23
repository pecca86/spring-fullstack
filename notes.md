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


# MAVEN & JIB (Pushing Docker images)
- ./mvnw clean install -P build-frontend -P jib-push-to-dockerhub -Dapp.image.tag=ver2


# DOCKER


# AWS
- Configure a docker-compose.yml file for elastic beanstalk inside project structure
- Go into AWS Elastic beanstalk and upload docker-compose file
- Configure Elastic Beanstalk DB:
    * Console -> Configuration -> Database -> Edit
- Configure Security Groups for DB access
    * AWS Console -> RDS -> DB instances -> Connectivity & security -> VPC Security Groups
    * Modify inbound / outbound rules
        - INBOUND RULES:
            * Source = Security Group name
            * Add rule:
                - Type: PostgreSQL, Source: MyIP
- Connect to database through docker:
    docker run -it --rm --network=db postgres:alpine psql -h <AWS_URI> -U pecca86 -d postgres
    (h = host, d = database)
- Deploy new Docker image to Elastic beanstalk


# SPRING DATA JPA

# CD / CI PIPELINE
- Github -> Github actions -> workflow -> 1. pull request (inner checks); ok to merge -> 2. merge to main, business logic (notification etc.), automation pipeline -> 
    ## GitHub Actions
    - create .github/workflows/build.yml && deploy.yml folder in code repo
    - 
    # AWS
    - Security Credentials -> User groups -> Create Group (Admin)
    -                      -> Users -> create User [enable programmatic acces]
                           -> Add new Secrets to github repository