package ru.practicum.shareit.request.dto;

import lombok.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.utils.Create;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RequestDto {
    private Long id;
    @NotNull(groups = {Create.class})
    private String description;
    private UserDto user;
    private LocalDateTime created;
    private List<ItemDto> items;
}