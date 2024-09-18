package com.example.assignment.Entity;

import com.example.assignment.Entity.Enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    private UUID clientID;

    @NotEmpty(message = "client name cannot be empty")
    @Length(min = 3, message = "name should have more than 2 characters")
    private String clientName;

    @NotEmpty(message = "contact info cannot be empty")
    @Length(min = 10, max = 10, message = "contact no. should have 10 digits only")
    private String contactInfo;

    @NotNull(message = "received Date cannot be empty")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate receivedDate;

    @NotEmpty(message = "inventory name cannot be empty")
    @Length(min = 3, message = "name should have more than 2 characters")
    private String inventoryReceived;

    @NotNull(message = "reported issue cannot be null")
    @Length(min = 3, message = "issue should have more than 2 characters")
    private String reportedIssues;

    @NotNull(message = "client notes cannot be null")
    private String clientNotes;

    @NotNull(message = "technician cannot be null")
    private String assignedTechnician;

    @NotNull(message = "est amount cannot be null")
    private double estimatedAmount;

    @NotNull(message = "deadline cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate deadline;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String Notes;

    @Column(nullable = true)
    private String inventoryURL;
}
