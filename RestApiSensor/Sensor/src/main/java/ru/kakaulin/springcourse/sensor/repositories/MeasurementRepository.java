package ru.kakaulin.springcourse.sensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kakaulin.springcourse.sensor.models.Measurement;


@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
