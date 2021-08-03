package mypackage.l10hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int number;
    private int floor;
    private long size;
    @ManyToOne
    private Hotel hotel;

    public Room(int number, int floor, long size, Hotel hotel) {
        this.number = number;
        this.floor = floor;
        this.size = size;
        this.hotel = hotel;
    }
}
