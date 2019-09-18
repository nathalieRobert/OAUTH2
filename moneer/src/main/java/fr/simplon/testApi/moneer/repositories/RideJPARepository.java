package fr.simplon.testApi.moneer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.simplon.testApi.moneer.entities.Ride;




/*This is the interface that uses the Java Persistence API to perform the JDBC operations*/
public interface RideJPARepository extends JpaRepository<Ride, Integer> {

}
