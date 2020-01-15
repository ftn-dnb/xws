package rs.ac.uns.ftn.xwsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.xwsservice.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);
}
