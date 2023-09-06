package it.busz.empik.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue
    private Long id;
    @Getter
    private String login;
    @Getter
    @Setter
    private Long requestCount;
}
