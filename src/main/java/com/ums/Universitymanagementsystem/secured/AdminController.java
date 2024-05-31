package com.ums.Universitymanagementsystem.secured;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String getAdmin() {
        return "Secured Endpoint :: GET - Admin controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    public String post() {
        return "Secured Endpoint :: POST - Admin controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public String delete() {
        return "Secured Endpoint :: DELETE - Admin controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public String update() {
        return "Secured Endpoint :: UPDATE - Admin controller";
    }

}
