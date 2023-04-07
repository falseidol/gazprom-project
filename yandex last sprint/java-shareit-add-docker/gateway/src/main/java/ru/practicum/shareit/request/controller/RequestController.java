package ru.practicum.shareit.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.RequestClient;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.utils.Create;
import ru.practicum.shareit.utils.MyPageRequest;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * TODO Sprint add-item-requests.
 */
@Controller
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class RequestController {
    private static final String HEADER = "X-Sharer-User-Id";
    private final RequestClient requestClient;

    @PostMapping
    public ResponseEntity<Object> createRequest(@RequestHeader(HEADER) Long userId,
                                                @Validated({Create.class})
                                                @RequestBody RequestDto requestDto) {
        return requestClient.saveRequest(userId, requestDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> findAllFromSize(@RequestHeader(HEADER) Long userId,
                                            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer from,
                                            @Positive @RequestParam(required = false, defaultValue = "10") Integer size) {
        Pageable pageable = MyPageRequest.makePageRequest(from,size, Sort.by(Sort.Direction.ASC, "created"));
        return requestClient.findAllFromSize(userId, from, size);
    }

    @GetMapping
    public ResponseEntity<Object> findAllByUserId(@RequestHeader(HEADER) Long userId) {
        return requestClient.findAllByUserId(userId);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getById(@RequestHeader(HEADER) Long userId, @PathVariable Long requestId) {
        return requestClient.getRequestById(userId, requestId);
    }
}