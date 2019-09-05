package com.turtles.sport_news.entity;

import javax.persistence.*;

@Entity
public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String imgURL;

    private String text;

    @ManyToOne
    private Category category;
}
