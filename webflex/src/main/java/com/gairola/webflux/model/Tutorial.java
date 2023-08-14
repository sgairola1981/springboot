package com.gairola.webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutorial {
    private int id;
    private String title;
    private String description;
    private boolean published;
}
