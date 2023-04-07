package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.ItemClient;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.utils.Create;
import ru.practicum.shareit.utils.MyPageRequest;
import ru.practicum.shareit.utils.Update;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collections;

import static ru.practicum.shareit.booking.controller.BookingController.HEADER;

/**
 * TODO Sprint add-controllers.
 */
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemClient itemClient;

    @PostMapping
    public ResponseEntity<Object> addItem(@RequestHeader(HEADER) Long userId,
                                          @Validated({Create.class})  @RequestBody ItemDto item) {
        return itemClient.addItem(userId, item);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> updateItem(@RequestHeader(HEADER) Long userId,
                                             @Validated({Update.class})
                                             @RequestBody ItemDto itemDto,
                                             @PathVariable Long itemId) {
        return itemClient.updateItem(userId, itemDto, itemId);
    }

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestHeader(HEADER) Long userId,
                                          @PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer from,
                                          @Positive @RequestParam(required = false, defaultValue = "10") Integer size) {
        Pageable pageable = MyPageRequest.makePageRequest(from, size, Sort.by(Sort.Direction.ASC, "id"));
        return itemClient.findAll(userId, from, size);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchItem(@RequestParam String text,
                                             @PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer from,
                                             @Positive @RequestParam(required = false, defaultValue = "10") Integer size) {
        if (text == null || text.isEmpty())
            return ResponseEntity.ok(Collections.emptyList());
        Pageable pageable = MyPageRequest.makePageRequest(from, size, Sort.by(Sort.Direction.ASC, "id"));
        return itemClient.searchItem(from, size, text);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getItemByIdAndUserId(@RequestHeader(HEADER) Long userId, @PathVariable Long id) {
        return itemClient.getItemById(userId, id);
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> postComment(@RequestHeader(HEADER) Long userId, @PathVariable Long itemId,
                                              @Validated({Create.class}) @RequestBody CommentDto commentDto) {
        return itemClient.postComment(userId, itemId, commentDto);
    }
}