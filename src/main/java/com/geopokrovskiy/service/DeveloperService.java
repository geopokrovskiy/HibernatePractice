package com.geopokrovskiy.service;

import com.geopokrovskiy.model.Developer;
import com.geopokrovskiy.model.Skill;
import com.geopokrovskiy.model.Speciality;
import com.geopokrovskiy.model.Status;
import com.geopokrovskiy.repository.DeveloperRepository;
import com.geopokrovskiy.repository.hibernate.DeveloperRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class DeveloperService {

    private DeveloperRepository developerRepository;

    public DeveloperService() {
        this.developerRepository = new DeveloperRepositoryImpl();
    }

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer addNewDev(Developer developer) {
        return this.developerRepository.addNew(developer);
    }

    public Developer getDevById(Long id) {
        return this.developerRepository.getById(id);
    }

    public List<Developer> getAllDevs() {
        return this.developerRepository.getAll().
                stream().
                filter(d -> d.getStatus().equals(Status.ACTIVE) && d.getSpeciality().getStatus().equals(Status.ACTIVE)).
                collect(Collectors.toList());
    }

    public Developer updateDevFirstName(Long id, String firstName) {
        Developer developer = getDevById(id);
        if (developer != null) {
            developer.setFirstName(firstName);
            return this.developerRepository.update(developer);
        }
        return null;
    }

    public Developer updateDevLastName(Long id, String lastName) {
        Developer developer = getDevById(id);
        if (developer != null) {
            developer.setLastName(lastName);
            return this.developerRepository.update(developer);
        }
        return null;
    }

    public Developer updateDevSpeciality(Long id, Speciality speciality) {
        Developer developer = getDevById(id);
        if (developer != null) {
            developer.setSpeciality(speciality);
            return this.developerRepository.update(developer);
        }
        return null;
    }

    public Developer updateDevSkills(Long id, List<Skill> skills) {
        Developer developer = getDevById(id);
        if (developer != null) {
            developer.setSkills(skills);
            return this.developerRepository.update(developer);
        }
        return null;
    }

    public boolean deleteDevById(Long id) {
        return this.developerRepository.delete(id);
    }
}
