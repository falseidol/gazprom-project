package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.appservice.Create;
import ru.practicum.shareit.appservice.MyPageRequest;
import ru.practicum.shareit.booking.BookingClient;
import ru.practicum.shareit.booking.dto.BookingDtoCreate;
import ru.practicum.shareit.exception.BebraException;
import ru.practicum.shareit.utils.BookingStatus;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * TODO Sprint add-bookings.
 */
@Controller
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingController {
    private final BookingClient bookingClient;
    public static final String HEADER = "X-Sharer-User-Id";

    @PostMapping
    public ResponseEntity<Object> addBooking(@RequestHeader(HEADER) Long userId,
                                             @Validated({Create.class})
                                             @RequestBody BookingDtoCreate bookingDtoCreate) {
        return bookingClient.addBooking(bookingDtoCreate, userId);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> updateBooking(@RequestHeader(HEADER) Long userId,
                                                @PathVariable Long bookingId,
                                                @RequestParam boolean approved) {
        return bookingClient.updateBooking(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> ownerGetBooking(@RequestHeader(HEADER) Long userId,
                                                  @PathVariable Long bookingId) {
        return bookingClient.getBookingByBookingId(userId, bookingId);
    }

    @GetMapping
    public ResponseEntity<Object> getBookingsForDefaultUser(@RequestHeader(HEADER) Long userId,
                                                            @RequestParam(defaultValue = "ALL", required = false) String state,
                                                            @PositiveOrZero @RequestParam(defaultValue = "0", required = false) Integer from,
                                                            @Positive @RequestParam(defaultValue = "10", required = false) Integer size) {
        Pageable pageable = MyPageRequest.makePageRequest(from, size, Sort.by(Sort.Direction.DESC, "id"));
        BookingStatus status;
        try {
            status = BookingStatus.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BebraException("Unknown state: " + state);
        }
        log.info("{}, {}, {}, {}", userId, status, from, size);
        return bookingClient.getBookingsForDefaultUser(userId, state, from, size);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getOwnersBookings(@RequestHeader(HEADER) Long userId,
                                                    @RequestParam(defaultValue = "ALL", required = false) String state,
                                                    @PositiveOrZero @RequestParam(defaultValue = "0", required = false) Integer from,
                                                    @Positive @RequestParam(defaultValue = "10", required = false) Integer size) {
        Pageable pageable = MyPageRequest.makePageRequest(from, size, Sort.by(Sort.Direction.DESC, "id"));
        BookingStatus status;
        try {
            status = BookingStatus.valueOf(state.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BebraException("Unknown state: " + state);
        }
        return bookingClient.findBookingsByOwner(userId, state, from, size);
    }
}