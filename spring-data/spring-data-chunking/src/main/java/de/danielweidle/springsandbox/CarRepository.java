package de.danielweidle.springsandbox;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.*;

@Repository
interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    @Query("SELECT c from Car c order by c.id")
    @QueryHints(value = {
            @QueryHint(name = HINT_FETCH_SIZE, value = "10"),
            @QueryHint(name = HINT_CACHEABLE, value = "false"),
            @QueryHint(name = HINT_READONLY, value = "true")
    })
    Stream<Car> findAllChunked();
}
