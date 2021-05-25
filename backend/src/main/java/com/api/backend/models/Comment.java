package com.api.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name="comment")
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String text;

    public Comment (String text) {
        this.text = text;
    }

    @ManyToOne()
    @JoinColumn(name = "post_id")
    private Post post;
}
