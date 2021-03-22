package com.reservationsystem.application;

import com.reservationsystem.domain.Region;
import com.reservationsystem.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

  //  @Autowired
    private RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository){
        this.regionRepository=regionRepository;
    }

    public List<Region> getRegions() {

        List<Region> regions = regionRepository.findAll();

        //regions.add(Region.builder().name("Seoul").build());
        return regions;
    }

    public Region addRegion(String name) {

        Region region = Region.builder().name(name).build();

        regionRepository.save(region);
        return region;
    }
}
