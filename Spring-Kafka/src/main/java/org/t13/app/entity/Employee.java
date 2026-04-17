package org.t13.app.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Employee {
    private String id;
    private String name;
}
