package net.brilliance.repository.sales;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.brilliance.domain.entity.sales.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

  Account findByNumber(String number);

}
