package ru.practicum.shareit.item.dto;

import lombok.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.utils.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id;
    @NotNull(groups = {Create.class})
    @NotBlank(groups = {Create.class})
    private String name;
    @NotNull(groups = {Create.class})
    @NotBlank(groups = {Create.class})
    private String description;
    @NotNull(groups = {Create.class})
    private Boolean available;
    private UserDto owner;
    private Long requestId;
}