package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoCreate;
import ru.practicum.shareit.booking.enums.BookingStatus;

import java.util.List;

public interface BookingService {
    BookingDto addBooking(Long userId, BookingDtoCreate bookingDtoCreate);

    BookingDto updateBooking(Long userId, Long bookingId, boolean approved);

    BookingDto getBookingByBookingId(Long userId, Long bookingId);

    List<BookingDto> getBookingsForDefaultUser(Long userId, BookingStatus state, Integer from, Integer size);

    List<BookingDto> findBookingsByOwner(Long userId, BookingStatus state,Integer from,Integer size);
}