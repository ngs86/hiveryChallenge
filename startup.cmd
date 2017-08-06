mongo ngsdb  --eval "db.dropDatabase()"
mongoimport --db ngsdb --collection people --jsonArray --file resources\people.json
mongoimport --db ngsdb --collection companies --jsonArray --file resources\companies.json
java -jar challenge-0.0.1-SNAPSHOT.jar
