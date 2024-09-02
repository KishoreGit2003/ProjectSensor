package com.example.Crud.model;
import jakarta.persistence.*;
@Entity
@Table(name="sensors")
public class Sensors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int customerId;
    private String sensor_name;
    private int sensor_type;
    private boolean inp_or_out;
    private int units;
    private int message_format;
    private String from_val;
    private String to_val;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCustomerId() { 
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Getter and Setter for sensor_name
    public String getSensor_name() {
        return sensor_name;
    }

    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
    }

    // Getter and Setter for sensor_type
    public int getSensor_type() {
        return sensor_type;
    }

    public void setSensor_type(int sensor_type) {
        this.sensor_type = sensor_type;
    }

    // Getter and Setter for inp_or_out
    public boolean isInp_or_out() {
        return inp_or_out;
    }

    public void setInp_or_out(boolean inp_or_out) {
        this.inp_or_out = inp_or_out;
    }

    // Getter and Setter for units
    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    // Getter and Setter for message_format
    public int getMessage_format() {
        return message_format;
    }

    public void setMessage_format(int message_format) {
        this.message_format = message_format;
    }

    // Getter and Setter for from_val
    public String getFrom_val() {
        return from_val;
    }

    public void setFrom_val(String from_val) {
        this.from_val = from_val;
    }

    // Getter and Setter for to_val
    public String getTo_val() {
        return to_val;
    }

    public void setTo_val(String to_val) {
        this.to_val = to_val;
    }

}
