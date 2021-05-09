package org.thoughtworks.induction.sample.service;

import org.springframework.stereotype.Service;
import org.thoughtworks.induction.sample.bean.Greeting;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SampleServiceImpl implements SampleService {

    private final AtomicInteger ID_COUNTER = new AtomicInteger();

    @Override
    public Greeting greet(String name) {
        return new Greeting(ID_COUNTER.incrementAndGet(), name.toUpperCase());
    }
}
