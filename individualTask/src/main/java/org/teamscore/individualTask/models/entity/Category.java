package org.teamscore.individualTask.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private String color;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Expense> expenses = new HashSet<>();

    //TODO : Добавить пользователя
}
