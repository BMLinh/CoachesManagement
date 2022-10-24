package com.hal.CoachesWeb.model;

import com.hal.CoachesWeb.entity.Coaches;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Coaches.class)
public class Coaches_ {
    public static volatile SingularAttribute<Coaches, Integer> id;
    public static volatile SingularAttribute<Coaches, LocalDateTime> startTime;
    public static volatile SingularAttribute<Coaches, LocalDateTime> endTime;
    public static volatile SingularAttribute<Coaches, Integer> price;
    public static volatile SingularAttribute<Coaches, Integer> emptySeat;
    public static volatile SingularAttribute<Coaches, Integer> coachId;
    public static volatile SingularAttribute<Coaches, Integer> startPoint;
    public static volatile SingularAttribute<Coaches, Integer> endPoint;
    public static volatile SingularAttribute<Coaches, Integer> status;

}
