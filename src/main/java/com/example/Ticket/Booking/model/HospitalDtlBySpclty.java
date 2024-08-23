package com.example.Ticket.Booking.model;

import lombok.Data;

import java.util.List;

@Data
public class HospitalDtlBySpclty {
private String hospitalName;
private List<Specalist> specalists;

}
