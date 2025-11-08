package org.sopt.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name ="article")
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Tag tag;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name ="content",  nullable = false, length = 1000)
    private String content;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public Article(Tag tag, String title, String content, Member member) {
        this.tag = tag;
        this.title = title;
        this.content = content;
        this.member = member;
    }
}