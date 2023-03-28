package de.danielweidle.springsandbox;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

interface UserRepository extends CrudRepository<User, Long> {

    interface UserView {
        String getName();

        String getKey();

        String getValue();
    }

    @Query(value = "SELECT app_user.name, user_attribute.key, user_attribute.value " +
            " FROM app_user inner join user_attribute on app_user.name = user_attribute.name " +
            " WHERE app_user.name = :name",
            countQuery = "select count(1) " +
                    " from app_user inner join user_attribute on app_user.name = user_attribute.name " +
                    "where app_user.name = :name",
            nativeQuery = true)
    Page<UserView> findUsersWithAttributePageable(@Param("name") String name, Pageable pageable);
}
