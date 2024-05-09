package ru.nsu.ccfit.student_compass.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "message")
@Getter
@Setter
@Accessors(chain = true)
public class Message {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long id;

    private String userName;

    private String text;

    @CreationTimestamp
    private Instant created;

    @ManyToMany
    @JoinTable(
        name = "message_user",
        joinColumns = {@JoinColumn(name = "message_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public void addViewed(User user) {
        users.add(user);
    }

    @Nonnull
    public Set<String> getViewedNames() {
        return users.stream().map(User::getFirstName).collect(Collectors.toSet());
    }
}
