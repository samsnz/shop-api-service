package com.shop.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.service.dtos.views.NearestCargoView;
import com.shop.service.models.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    @Query(value = "SELECT id, code, name, details, longitude, latitude, ROUND(ST_Distance_Sphere(point(longitude, latitude), point( :longitude, :latitude))/1000, 2) AS distance FROM soft_shop.cargo WHERE (ST_Distance_Sphere(point(longitude, latitude), point( :longitude, :latitude))/1000) <= :distance ORDER BY distance ASC LIMIT 1", nativeQuery = true)
    NearestCargoView findNearestCargoWithin(Double longitude, Double latitude, Double distance);

    @Query(value = "SELECT id, code, name, details, longitude, latitude, ROUND(ST_Distance_Sphere(point(longitude, latitude), point( :longitude, :latitude))/1000, 2) AS distance FROM soft_shop.cargo ORDER BY distance ASC LIMIT :limit", nativeQuery = true)
    List<NearestCargoView> findTopClosestCargo(Double longitude, Double latitude, Integer limit);

}
