package ru.practicum.shareit.booking.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDtoCreate {
    private LocalDateTime start;
    private LocalDateTime end;
    private Long itemId;
}