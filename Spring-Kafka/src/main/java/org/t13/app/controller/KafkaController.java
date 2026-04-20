package org.t13.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.t13.app.entity.Employee;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    private final List<Employee> employees = new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/postKafka")
    public Response postKafka(@QueryParam("name") String name, @QueryParam("id") String id) {
        Employee employee = Employee.builder().id(id).name(name).build();
        kafkaProducer.send(new ProducerRecord("employeeTopic", employee.getId(), employee));
        return Response.ok().entity("Posted Successfully").build();
    }

    @GetMapping("/getKafka")
    @KafkaListener(topics = "employeeTopic")
    public Response getKafka(String message) {
        Employee employee = objectMapper.convertValue(message, Employee.class);
        employees.add(employee);
        return  Response.ok().entity(employees).build();
    }
}
