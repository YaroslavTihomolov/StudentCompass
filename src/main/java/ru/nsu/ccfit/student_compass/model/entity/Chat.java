package ru.nsu.ccfit.student_compass.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chat")
@Getter
@Setter
@Accessors(chain = true)
public class Chat {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private Long id;

    private String name;

    @BatchSize(size = 10)
    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> user = new HashSet<>();

    @Setter(value = AccessLevel.PRIVATE)
    @Fetch(FetchMode.JOIN)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Message> userMessages = new ArrayList<>();

    @Nonnull
    public Chat addMessage(Message userMessage) {
        userMessages.add(userMessage);
        userMessage.setChat(this);
        return this;
    }
}
