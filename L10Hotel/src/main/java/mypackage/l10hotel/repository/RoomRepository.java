package mypackage.l10hotel.repository;

import mypackage.l10hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    boolean existsRoomByNumberAndFloorAndAndHotel_Id(int number,int floor, Integer hotelId);


}
