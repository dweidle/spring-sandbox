package de.danielweidle.springsandbox;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAttributeRepository attributeRepository;

    @Test
    void name() {
        attributeRepository.save(attribute("freddy"));
        attributeRepository.save(attribute("freddy"));
        attributeRepository.save(attribute("olaf"));
        userRepository.save(user("freddy"));
        userRepository.save(user("olaf"));

        Page<UserRepository.UserView> freddy = userRepository.findUsersWithAttributePageable("freddy", Pageable.ofSize(1));
        assertThat(freddy).isNotNull();
    }

    private User user(String user) {
        return User.builder().name(user).build();
    }
    private UserAttribute attribute(String user) {
        return UserAttribute.builder().name(user).value("key").value(UUID.randomUUID().toString()).build();
    }
}