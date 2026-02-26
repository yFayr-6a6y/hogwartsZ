package hogwarts.demo.controller;

import hogwarts.demo.model.Avatar;
import hogwarts.demo.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    // Критерий оценки: два параметра через @RequestParam
    @GetMapping
    public ResponseEntity<Page<Avatar>> getAllAvatars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<Avatar> avatars = avatarService.getAllAvatars(pageable);
        return ResponseEntity.ok(avatars);
    }

    // ... остальные методы контроллера ...
}