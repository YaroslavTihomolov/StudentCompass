package ru.nsu.ccfit.student_compass.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

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

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
