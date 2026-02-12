package org.t13.app.api;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.t13.app.student.GetStudentRequest;
import org.t13.app.student.GetStudentResponse;

@Endpoint
public class StudentEndpoint {

    private static final String NAMESPACE_URI = "http://www.app.t13.org/student";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetStudentRequest")
    @ResponsePayload
    public GetStudentResponse getStudent(@RequestPayload GetStudentRequest request) {
        GetStudentResponse response = new GetStudentResponse();
        response.setName("Student #" + request.getId());
        response.setAge(21);
        return response;
    }
}


