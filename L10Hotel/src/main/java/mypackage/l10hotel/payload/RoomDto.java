package mypackage.l10hotel.payload;

import lombok.Data;

@Data
public class RoomDto {
    private int number;
    private int floor;
    private long size;
    private int hotelId;

}
