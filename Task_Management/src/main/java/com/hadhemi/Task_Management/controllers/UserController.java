package com.hadhemi.Task_Management.controllers;

import com.hadhemi.Task_Management.dto.UserDTO;
import com.hadhemi.Task_Management.enums.Role;
import com.hadhemi.Task_Management.models.User;
import com.hadhemi.Task_Management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Api("/user")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;





    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping
    public ResponseEntity<UserDTO> createUserWithImageAndPermissions(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("position") String position,
            @RequestParam(value = "profileImage") MultipartFile profileImage,
            @RequestParam(value = "permissions", required = false) List<String> permissions,
            @RequestParam("organizationId") String organizationId,
            @RequestParam(value ="ajouteTask" , required = false) boolean ajouteTask,
            @RequestParam(value ="supprimeTask", required = false) boolean supprimeTask
    ) {
        UserDTO createdUserDTO = userService.createUserWithImageAndPermissions(name, email, password, position, profileImage, permissions, organizationId, ajouteTask, supprimeTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }



    @PostMapping("/admin")
    public ResponseEntity<UserDTO> createAdminWithImage(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("position") String position,
            @RequestParam("profileImage") MultipartFile profileImage
    ) {

        UserDTO createdUserDTO = userService.createAdminWithImage(name, email,password,position, profileImage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }

    @GetMapping("/by-role")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@RequestParam Role role) {
        List<UserDTO> usersByRole = userService.getUsersByRole(role);
        return ResponseEntity.ok(usersByRole);
    }
    @GetMapping("/by-role-and-organization")
    public ResponseEntity<List<User>> getUsersByRoleAndOrganization(
            @RequestParam Role role,
            @RequestParam String organizationId) {
        List<User> users = userService.getUsersByRoleAndOrganization(role, organizationId);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId) {
        UserDTO userDTO = userService.getUser(userId);
        return ResponseEntity.ok(userDTO);
    }
//    @PutMapping("/{userId}/status")
//    public ResponseEntity<User> updateUserStatus(@PathVariable String userId, @RequestBody User request) {
//        User updatedUser = userService.updateUserStatus(userId, request.isValidated());
//        return ResponseEntity.ok(updatedUser);
//    }

    @PutMapping("/{userId}/status-and-organization")
    public ResponseEntity<User> updateUserStatusAndAssignOrganization(
            @PathVariable String userId,
            @RequestBody User request) {
        User updatedUser = userService.updateUserStatusAndAssignOrganization(userId, request, request.isValidated());
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable String userId,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("position") String position,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestParam(value = "permissions", required = false) List<String> permissions,
            @RequestParam(value ="ajouteTask" , required = false) boolean ajouteTask,
            @RequestParam(value ="supprimeTask", required = false) boolean supprimeTask) {

        UserDTO updatedUserDTO = userService.updateUserWithImageAndPermissions(
                userId, name, email, password, position, profileImage, permissions, ajouteTask, supprimeTask);

        return ResponseEntity.ok(updatedUserDTO);
    }



    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}




