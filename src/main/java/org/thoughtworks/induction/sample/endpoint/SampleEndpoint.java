package org.thoughtworks.induction.sample.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thoughtworks.induction.sample.bean.Greeting;
import org.thoughtworks.induction.sample.service.SampleService;

@RestController
public class SampleEndpoint {

    @Autowired
    private SampleService sampleService;

    @GetMapping(value = "/greet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greeting> greet(@RequestParam String name) {
        return ResponseEntity
                .ok()
                .body(this.sampleService.greet(name));
    }
}
