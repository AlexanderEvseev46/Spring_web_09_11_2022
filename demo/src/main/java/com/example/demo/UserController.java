package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final List<User> listOfUsers= new ArrayList<>();
//curl -X POST http://localhost:8080/addUser -d "{\"name\" : \"Ivan\", \"age\" : 17}" -H "Content-Type: application/json"
    @PostMapping("addUser")
    public ResponseEntity<Void> addUser(@RequestBody User user){
        listOfUsers.add(user);
        return ResponseEntity.accepted().build();
    }
    //curl -X DELETE http://localhost:8080/deleteUser/0 -H "Content-Type: application/json"
    @DeleteMapping("deleteUser/{index}")
    public ResponseEntity<Void> deleteUser(@PathVariable ("index") Integer index){
        listOfUsers.remove(index);
        return ResponseEntity.accepted().build();
    }
    //curl -X GET http://localhost:8080/getUser/0 -H "Content-Type: application/json"
    @GetMapping("getUser/{index}")
    public ResponseEntity<User> getUser(@PathVariable ("index") Integer index){
        return ResponseEntity.ok(listOfUsers.get(index));
    }
    //curl -X GET http://localhost:8080/getAllUsers -H "Content-Type: application/json"
    @GetMapping("getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(listOfUsers);
    }
    //curl -X PUT http://localhost:8080/rename/0 -d "{\"age\" : 12}" -H "Content-Type: application/json"
    @PutMapping("rename/{index}")
    public ResponseEntity<Void> rename(@RequestBody User user1,
                                       @PathVariable("index") Integer index){
        listOfUsers.get(index).setAge(user1.getAge());
        return ResponseEntity.accepted().build();
    }

}
