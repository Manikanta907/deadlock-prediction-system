package com.komal.komal.controller;

import com.komal.komal.dto.DeadlockResponse;
import com.komal.komal.model.*;
import com.komal.komal.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DeadlockController {

    private final DeadlockService deadlockService;
    private final BankerService bankerService;

    public DeadlockController(DeadlockService d, BankerService b) {
        this.deadlockService = d;
        this.bankerService = b;
    }

    @PostMapping("/detect")
    public DeadlockResponse detect(@RequestBody ProcessRequest req) {

        boolean result = deadlockService.detectDeadlock(req.edges);

        return new DeadlockResponse(
                result,
                result ? "🔥 Deadlock Detected" : "✅ No Deadlock"
        );
    }

    @PostMapping("/banker")
    public String banker(@RequestBody BankerRequest req) {
        return bankerService.checkSafeState(
                req.allocation,
                req.max,
                req.available
        );
    }
}