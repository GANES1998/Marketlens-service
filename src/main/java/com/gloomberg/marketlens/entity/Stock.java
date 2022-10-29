package com.gloomberg.marketlens.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Stock {

    @Id
    String symbol;

    @Id
    Date day;



}
