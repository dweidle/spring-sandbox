package de.danielweidle.springsandbox;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
class CarPersistenceAdapter {
    private final CarRepository carRepository;

    @Transactional(readOnly = true)
    public void findCarsChunked() {
        Stream<Car> cars = carRepository.findAllChunked();
        cars.forEach(System.out::println);
    }

}
