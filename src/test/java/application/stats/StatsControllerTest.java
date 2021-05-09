package application.stats;

import static application.TestUtils.GUEST_SESSION_TOKEN_1;
import static application.TestUtils.GUEST_SESSION_TOKEN_2;
import static application.TestUtils.GUEST_USERNAME;
import static application.TestUtils.USER_1_SESSION_TOKEN;
import static application.TestUtils.USER_1_USERNAME;
import static application.TestUtils.USER_2_SESSION_TOKEN;
import static application.TestUtils.USER_2_USERNAME;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.matag.admin.stats.StatsResponse;

import application.AbstractApplicationTest;

public class StatsControllerTest extends AbstractApplicationTest {
  @Test
  public void shouldGetStatsAsUnauthenticatedUser() {
    // When
    var response = restTemplate.getForEntity("/stats", StatsResponse.class);

    // Then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  public void shouldGetTotalUsers() {
    // When
    var response = restTemplate.getForObject("/stats", StatsResponse.class);

    // Then
    assertThat(response.getTotalUsers()).isEqualTo(3);
  }

  @Test
  public void shouldGetOnlineUsers() {
    // Given
    loginUser(USER_1_SESSION_TOKEN, USER_1_USERNAME);
    setCurrentTime(LocalDateTime.parse("2000-01-01T00:00:00"));
    loginUser(USER_2_SESSION_TOKEN, USER_2_USERNAME);
    setCurrentTime(TEST_START_TIME);
    loginUser(GUEST_SESSION_TOKEN_1, GUEST_USERNAME);
    loginUser(GUEST_SESSION_TOKEN_2, GUEST_USERNAME);

    // When
    var response = restTemplate.getForObject("/stats", StatsResponse.class);

    // Then
    assertThat(response.getOnlineUsers()).containsExactlyInAnyOrder("User1", "Guest", "Guest");
  }

  @Test
  public void shouldGetNumOfCards() {
    // When
    var response = restTemplate.getForObject("/stats", StatsResponse.class);

    // Then
    assertThat(response.getTotalCards()).isGreaterThan(100);
  }

  @Test
  public void shouldGetNumOfSets() {
    // When
    var response = restTemplate.getForObject("/stats", StatsResponse.class);

    // Then
    assertThat(response.getTotalSets()).isGreaterThan(10);
  }
}