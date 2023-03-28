package de.danielweidle.springsandbox;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "app_user")
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
}
