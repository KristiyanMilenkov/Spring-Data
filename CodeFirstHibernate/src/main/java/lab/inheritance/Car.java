package lab.inheritance;

import jakarta.persistence.*;
import lab.composition.PlateNumber;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {
    private static final String CAR_TYPE = "CAR";
    @Basic
    private int seats;
    @OneToOne
    @JoinColumn(name = "plate_number_id",
    referencedColumnName = "id")
    private PlateNumber plateNumber;

    public Car(String model, BigDecimal price, String fuelType, int seats, PlateNumber plateNumber) {
        super(CAR_TYPE, model, price, fuelType);
        this.seats = seats;
        this.plateNumber = plateNumber;
    }
    public Car() {
    }


}
