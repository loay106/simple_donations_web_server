package com.dagshub.assignments.donations.integration;

import com.dagshub.assignments.donations.DonationsApplication;
import com.dagshub.assignments.donations.model.Donation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DonationsApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DonationsTest {

  private static final String UPSERT_DONATION_URL = "/donations/add";
  private static final String GET_DONATION_URL = "/donations/get/%s";
  private static final String GET_AMOUNT_URL = "/donations/amount";

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void getDonation() {
    Donation expectedDonation = addDonation();
    ResponseEntity<Donation> donationResponseEntity = restTemplate.getForEntity(String.format(GET_DONATION_URL, expectedDonation.getId()), Donation.class);
    assertThat(donationResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    Donation actualDonation = donationResponseEntity.getBody();
    assertThat(actualDonation).isEqualTo(expectedDonation);
  }
  private Donation addDonation() {
    ResponseEntity<Donation> donationResponseEntity = restTemplate.postForEntity(UPSERT_DONATION_URL, 5.3, Donation.class);
    assertThat(donationResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    return donationResponseEntity.getBody();
  }
}
