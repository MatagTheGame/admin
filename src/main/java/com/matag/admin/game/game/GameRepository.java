package com.matag.admin.game.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
  List<Game> findByTypeAndStatus(GameType type, GameStatusType status);
}
