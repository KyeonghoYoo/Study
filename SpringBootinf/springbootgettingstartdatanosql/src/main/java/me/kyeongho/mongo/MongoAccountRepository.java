package me.kyeongho.mongo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoAccountRepository extends MongoRepository<MongoAccount, String>{

	Optional<MongoAccount> findByEmail(String id);

}
