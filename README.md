# BOMRecommendation
This repository is periodically compiled and tested using Jenkins and Maven. In order to run Maven, a command like the following must be issued
` apache-maven-3.6.0/bin/mvn clean install -f BOMRecommendation/pom.xml -Dmaven.test.skip=true `
Skip tests, otherwise tests will fail without DB connection
