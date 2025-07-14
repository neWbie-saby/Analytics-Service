package com.leaderboard.analytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leaderboard.analytics.entity.MatchUser;
import com.leaderboard.analytics.entity.MatchUserId;

@Repository
public interface MatchUserRepository extends JpaRepository<MatchUser, MatchUserId> {

    // Custom query method - Spring Data JPA will implement this automatically
    // Note: id.matchId will be used because id is the composite key
    List<MatchUser> findByIdMatchId(Long matchId);

    //Custom query with JPQL
    @Query("SELECT mu from MatchUser mu JOIN FETCH mu.user WHERE mu.id.matchId = :matchId ORDER BY mu.score DESC")
    List<MatchUser> findByMatchIdWithUserDetails(@Param("matchId") Long matchId);

    //Native SQL Query example - scenario - JSON_EXTRACT, GEOMETRY - where JPQL cannot be used
    @Query(value = "SELECT * FROM match_users WHERE match_id = :matchId ORDER BY score DESC", nativeQuery = true)
    List<MatchUser> findByMatchIdNative(@Param("matchId") Long matchId);

    //JPQL to query a user's score in a match
    @Query("SELECT mu from MatchUser mu WHERE mu.id.matchId = :matchId AND mu.id.userId = :userId")
    MatchUser findByMatchIdAndUserId(@Param("matchId") Long matchId, @Param("userId") Long userId);
}
