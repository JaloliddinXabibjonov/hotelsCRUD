package mypackage.l10hotel.controller;

import mypackage.l10hotel.entity.Hotel;
import mypackage.l10hotel.entity.Room;
import mypackage.l10hotel.payload.RoomDto;
import mypackage.l10hotel.repository.HotelRepository;
import mypackage.l10hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
        boolean existsRoomByNumberAndFloorAndAndHotel_id = roomRepository.existsRoomByNumberAndFloorAndAndHotel_Id(roomDto.getNumber(), roomDto.getFloor(), roomDto.getHotelId());
        if (existsRoomByNumberAndFloorAndAndHotel_id)
            return "This room number, floor number and hotel already exist";
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()){
            return "Hotel not found";
        }
        Hotel hotel = optionalHotel.get();
        Room room=new Room(roomDto.getNumber(), roomDto.getFloor(), roomDto.getSize(),hotel);
        roomRepository.save(room);
        return "Room added";
    }
    @GetMapping
    public List<Room> getRooms(){
        return roomRepository.findAll();
    }
    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()){
            return "Room not found";
        }
        Room room = optionalRoom.get();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent())
            return "Hotel not found";
        Hotel hotel = optionalHotel.get();
        room.setHotel(hotel);
        roomRepository.save(room);
        return "Room edited";
    }
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "Room not found";
        roomRepository.deleteById(id);
        return "Room deleted";
    }

}
