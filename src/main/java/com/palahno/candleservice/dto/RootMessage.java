package com.palahno.candleservice.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Artem Palahno
 */
@Data
public class RootMessage {
    private List<Stock> data;
    private String type;
}
