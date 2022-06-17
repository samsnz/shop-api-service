package com.shop.service.repositories;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.service.dtos.CargoDrinkDto;
import com.shop.service.dtos.views.NearestCargoView;
import com.shop.service.models.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Cargo findByCode(String code);

    @Query(value = "SELECT id, code, name, details, longitude, latitude, ROUND(ST_Distance_Sphere(point(longitude, latitude), point( :longitude, :latitude))/1000, 2) AS distance FROM cargo WHERE (ST_Distance_Sphere(point(longitude, latitude), point( :longitude, :latitude))/1000) <= :distance ORDER BY distance ASC LIMIT 1", nativeQuery = true)
    NearestCargoView findNearestCargoWithin(Double longitude, Double latitude, Double distance);

    @Query(value = "SELECT id, code, name, details, longitude, latitude, ROUND(ST_Distance_Sphere(point(longitude, latitude), point( :longitude, :latitude))/1000, 2) AS distance FROM cargo ORDER BY distance ASC LIMIT :limit", nativeQuery = true)
    List<NearestCargoView> findTopClosestCargo(Double longitude, Double latitude, Integer limit);

    @Query("SELECT new com.shop.service.dtos.CargoDrinkDto(a.drink.id, a.drink.code, a.drink.name) FROM OrderDrink a WHERE a.order.cargo.code = :cargoCode AND a.order.date BETWEEN :startDate AND :endDate")
    Set<CargoDrinkDto> findAllTransportedDrinksByCargoWithinDates(Date startDate, Date endDate, String cargoCode);

}
