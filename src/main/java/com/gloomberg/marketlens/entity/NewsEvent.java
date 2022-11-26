package com.gloomberg.marketlens.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NEWS_EVENTS")
@Data
public class NewsEvent {

    @Id
    String event;

    @Column(name = "EVENT_DESCRIPTION")
    String eventDescription;

    @Column(name = "START_YEAR")
    int startYear;

    @Column(name = "END_YEAR")
    int endYear;

}
