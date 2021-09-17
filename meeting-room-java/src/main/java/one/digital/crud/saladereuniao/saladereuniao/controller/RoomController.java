package one.digital.crud.saladereuniao.saladereuniao.controller;

import one.digital.crud.saladereuniao.saladereuniao.exception.ResourceNotFoundException;
import one.digital.crud.saladereuniao.saladereuniao.model.Room;
import one.digital.crud.saladereuniao.saladereuniao.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found::" + id));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping
    public Room createRoom(@RequestBody @Valid Room room) {
        return roomRepository.save(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable("id") Long id, @RequestBody @Valid Room incomingRoom) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found::" + id));
        room.setName(incomingRoom.getName());
        room.setDate(incomingRoom.getDate());
        room.setStartHour(incomingRoom.getStartHour());
        room.setEndHour(incomingRoom.getEndHour());
        return ResponseEntity.ok().body(roomRepository.save(room));
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable("id") Long id) throws ResourceNotFoundException{
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found::" + id));
        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
