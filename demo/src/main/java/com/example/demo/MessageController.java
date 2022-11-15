package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class MessageController {
    private final List<String> messages = new ArrayList<>();
    //curl -X GET http://localhost:8080/messages?text="hello"
    @GetMapping("messages")
    public ResponseEntity<List<String>> getMessages(@RequestParam String text) {
        List<String> filtredMessages = new ArrayList<>();
        for (int i = 0; i < messages.size(); i ++){
            if(messages.get(i).startsWith(text))
                filtredMessages.add(messages.get(i));
        }
        return ResponseEntity.ok(filtredMessages);
    }
    //curl -X POST -d "hello world" http://localhost:8080/messages
    @PostMapping("messages")
    public ResponseEntity<Void> addMessage(@RequestBody String text) {
        messages.add(text);
        return ResponseEntity.accepted().build();
    }
    //curl -X GET http://localhost:8080/messages/0
    @GetMapping("messages/{index}")
    public ResponseEntity<String> getMessage(@PathVariable("index") Integer
                                                     index) {
        return ResponseEntity.ok(messages.get(index));
    }
    //curl -X DELETE http://localhost:8080/messages/0
    @DeleteMapping("messages/{index}")
    public ResponseEntity<Void> deleteText(@PathVariable("index") Integer
                                                   index) {
        messages.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    //curl -X PUT http://localhost:8080/messages/0
    @PutMapping("messages/{index}")
    public ResponseEntity<Void> updateMessage(
            @PathVariable("index") Integer i,
            @RequestBody String message) {
        messages.remove((int) i);
        messages.add(i, message);
        return ResponseEntity.accepted().build();
    }
    //curl -X GET http://localhost:8080/messages/search/"hello"
    @GetMapping("messages/search/{text}")
    public ResponseEntity<Integer> searchText(@PathVariable("text") String text){
        for(int i = 0; i < messages.size(); i++){
            if (messages.get(i).contains(text)){
                return ResponseEntity.ok(i);
            }
        }
        return ResponseEntity.ok(-1);
    }
    //curl -X GET http://localhost:8080/messages/count
    @GetMapping("messages/count")
    //curl -X GET http://localhost:8080/messages/count
    public ResponseEntity<Integer> getSize(){
        return ResponseEntity.ok(messages.size());
    }
    //curl -X POST -d ПРИВЕТ http://localhost:8080/messages/1/create
    @PostMapping("messages/{index}/create")
    public ResponseEntity<Void> doAdd(
            @RequestBody String text,
            @PathVariable("index") Integer index){
        messages.add(index, text);
        return ResponseEntity.accepted().build();
    }
    //curl -X DELETE http://localhost:8080/messages/search/hello
    @DeleteMapping("messages/search/{text}")
    public ResponseEntity<Void> deletealltext(@PathVariable("text") String text){
        for(int i = 0; i < messages.size(); i ++){
            if(messages.get(i).contains(text)){
                messages.remove(i);
            }
        }
        return ResponseEntity.accepted().build();
    }

}
