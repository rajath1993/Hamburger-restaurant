package com.hamburger.administration.repository;




import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hamburger.administration.model.Reservation;

/**
 * @author Rajath
 *
 */

@Repository
public interface ReservationRepository extends MongoRepository<Reservation,String>{
	
}
