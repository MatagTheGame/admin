package com.matag.admin.game.score;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.matag.admin.game.game.GameType;
import com.matag.admin.user.MatagUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "score")
public class Score {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  @JoinColumn(referencedColumnName = "id")
  private MatagUser matagUser;
  @Enumerated(EnumType.STRING)
  private GameType type;
  private Integer elo;
  private Integer wins;
  private Integer draws;
  private Integer losses;
  private LocalDateTime lastGamePlayedAt;

  @JsonPOJOBuilder(withPrefix = "")
  public static class GameScoreBuilder {

  }
}
