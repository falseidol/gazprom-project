package ru.practicum.shareit.item.dto;

import lombok.*;
import ru.practicum.shareit.user.model.User;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private User owner;
    private Long requestId;
}