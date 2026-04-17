package org.t13.app.controller;

import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.t13.app.entity.Employee;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/postKafka")
    public Response postKafka(@QueryParam("name") String name, @QueryParam("id") String id) {
        Employee employee = Employee.builder().id(id).name(name).build();
        kafkaProducer.send(new ProducerRecord("topic", employee.getId(), employee));
        return Response.ok().entity("Posted Successfully").build();
    }
}
