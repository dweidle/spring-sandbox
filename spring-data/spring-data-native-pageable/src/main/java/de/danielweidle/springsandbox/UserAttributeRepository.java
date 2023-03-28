package de.danielweidle.springsandbox;

import org.springframework.data.repository.CrudRepository;

interface UserAttributeRepository extends CrudRepository<UserAttribute, Long> {
}
