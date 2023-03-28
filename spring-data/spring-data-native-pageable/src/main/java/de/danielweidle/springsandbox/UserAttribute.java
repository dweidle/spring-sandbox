package de.danielweidle.springsandbox;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_attribute")
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class UserAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String key;
    private String value;
}
