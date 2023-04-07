package ru.practicum.shareit.request.dto;

import lombok.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO Sprint add-item-requests.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RequestDto {
    private Long id;
    private String description;
    private User user;
    private LocalDateTime created;
    private List<ItemDto> items;
}