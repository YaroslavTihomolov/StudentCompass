package ru.nsu.ccfit.student_compass.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @Fetch(FetchMode.JOIN)
    private Set<User> viewedSet;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public void addViewed(User user) {
        viewedSet.add(user);
    }

    @Nonnull
    public Set<String> getViewedNames() {
        return viewedSet.stream().map(User::getFirstName).collect(Collectors.toSet());
    }
}
