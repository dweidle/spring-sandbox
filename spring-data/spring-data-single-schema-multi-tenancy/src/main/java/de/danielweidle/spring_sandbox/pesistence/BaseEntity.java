package de.danielweidle.spring_sandbox.pesistence;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@MappedSuperclass
@Getter
class BaseEntity {
    private String productionLine;
}
