CA2 backend

To use the project follow the steps

- Change name, artifactId, dbname and remote server in pom.xml
- Add REMOTE_USER and REMOTE_PW on github
- Check name of branch in mavenworkflow.yml and change if it doesn't match (either master or main)
- Add datasource and change persistence.xml (don't commit these changes to git)
- Run SetupTestUsers.java (Change passwords for droplet and don't push those changes)