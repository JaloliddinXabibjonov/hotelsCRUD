package mypackage.l10hotel.controller;

import mypackage.l10hotel.entity.Hotel;
import mypackage.l10hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        hotelRepository.save(hotel);
        return "Hotel saved";
    }
    @GetMapping
    public List<Hotel> getHotel(){
        return hotelRepository.findAll();

    }
    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            Hotel editingHotel = optionalHotel.get();
            editingHotel.setName(hotel.getName());
            hotelRepository.save(editingHotel);
            return "Hotel edited";
        }
        return "Hotel not found";
    }
    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent())
            return "Hotel not found";
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }


}
