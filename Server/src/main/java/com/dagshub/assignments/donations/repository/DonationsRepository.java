package com.dagshub.assignments.donations.repository;

import com.dagshub.assignments.donations.entity.DonationDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DonationsRepository extends MongoRepository<DonationDo, String> {

}
