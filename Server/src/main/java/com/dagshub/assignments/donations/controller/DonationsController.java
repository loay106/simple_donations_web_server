package com.dagshub.assignments.donations.controller;

import com.dagshub.assignments.donations.entity.DonationDo;
import com.dagshub.assignments.donations.model.Donation;
import com.dagshub.assignments.donations.repository.DonationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/donations")
public class DonationsController {

  @Autowired
  private DonationsRepository repository;

  @RequestMapping(path = "/add", method = RequestMethod.POST)
  public ResponseEntity<Donation> upsert(@RequestBody Integer amount) {
    if(amount <= 0){
      return ResponseEntity.badRequest().build();
    }
    Donation donation = new Donation(amount);
    DonationDo normalizedDonation = new DonationDo(donation.getId(),donation.getAmount(),donation.getDate());
    DonationDo res = repository.save(normalizedDonation);
    return ResponseEntity.ok(new Donation(res.getId(),res.getAmount(),res.getDate()));
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<Donation> getDonationByID(@PathVariable String id) {
    Optional<DonationDo> donation = repository.findById(id);
    return donation.map(donationDo -> ResponseEntity.ok(new Donation(donationDo.getId(), donationDo.getAmount(), donationDo.getDate())))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/amount")
  public ResponseEntity<Integer> getDonationTotalAmount() {
    Integer donationsAmount = repository
            .findAll()
            .stream()
            .mapToInt(DonationDo::getAmount)
            .sum();
    return ResponseEntity.ok(donationsAmount);
  }

}
