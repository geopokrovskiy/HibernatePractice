package com.geopokrovskiy.service;

import com.geopokrovskiy.model.Speciality;
import com.geopokrovskiy.model.Status;
import com.geopokrovskiy.repository.SpecialityRepository;
import com.geopokrovskiy.repository.hibernate.SpecialityRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class SpecialityService {

    private SpecialityRepository specialityRepository;

    public SpecialityService() {
        this.specialityRepository = new SpecialityRepositoryImpl();
    }

    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public Speciality addNewSpec(Speciality speciality) {
        return this.specialityRepository.addNew(speciality);
    }

    public Speciality getSpecById(Long id) {
        return this.specialityRepository.getById(id);
    }

    public List<Speciality> getAllSpecs() {
        return this.specialityRepository.getAll()
                .stream().
                filter(s -> s.getStatus().equals(Status.ACTIVE))
                .collect(Collectors.toList());
    }

    public Speciality updateSpecName(Long id, String name) {
        Speciality speciality = getSpecById(id);
        if (speciality != null) {
            speciality.setName(name);
            return this.specialityRepository.update(speciality);
        }
        return null;
    }

    public boolean deleteSpecById(Long id) {
        return this.specialityRepository.delete(id);
    }
}
