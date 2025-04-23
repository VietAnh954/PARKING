package vn.bacon.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.bacon.parking.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Optional <Account> findById(String maTK);
    // Optional findById (String maTK);
    // Optional<Account> findByUsername(String username);

}
