# XML i web servisi
Projekat iz predmeta XML i web servisi.  
Tim 05

Članovi tima:
* Boris Šuličenko SW 4/2016
* David Stanojević SW 41/2016
* Nemanja Miković SW 68/2016

# Uputstvo za pokretanje projekta

Skinuti [MySQL](https://www.mysql.com/) i [eXist](http://exist-db.org/exist/apps/homepage/index.html) baze podataka.  

U eXist bazi promeniti port na kome ce raditi u 8899 kao sto je opisano [ovde](https://exist-db.org/exist/apps/doc/troubleshooting) u sekciji Port Conflicts.

Pokrenuti MySQL bazu podataka.

Klonirati repozitorijum:
```
git clone https://github.com/ftn-dnb/xws
cd xws
```

Da bi se backend pokrenuo potrebno je promeniti sledece parametre u ```application.properties``` fajlu koji se nalazi na lokaciji ```/xws-service/src/main/resources```:
- spring.mail.username = EMAIL
- spring.mail.password = EMAIL_PASSWORD
- spring.datasource.username = MYSQL_DB_USERNAME
- spring.datasource.password = MYSQL_DB_PASSWORD

Nakon ovoga se preko Maven-a moze pokrenuti backend aplikacija:
```
cd xws-service
mvn spring-boot:run
```

Servis ce biti pokrenut na portu 8080.

Da bi se pokrenula frontend aplikacija treba instalirati [Angular](https://angular.io/).

```
cd xws-client
npm install
ng serve
```

Frontend aplikacija ce biti pokrenuta na portu 4200.
