package repository;

import entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

	/**
	 * Method to fetch the employee details on the basis of designation by using
	 * Elastic-Search-Repository.
	 * 
	 * @param designation
	 * @return
	 */

//     public List<Ad> findByRequestId(String id);

}