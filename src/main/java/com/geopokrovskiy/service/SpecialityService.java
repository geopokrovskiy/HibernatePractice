package com.geopokrovskiy.service;

import com.geopokrovskiy.DAO.DAO;
import com.geopokrovskiy.model.Speciality;
import java.util.List;

public class SpecialityService {

    public Speciality addNewSpec(Speciality speciality) {
        DAO.addObject(speciality);
        return speciality;
    }

    public Speciality getSpecById(Long id) {
        Speciality speciality = (Speciality) DAO.getObjectById(id, Speciality.class);
        DAO.closeOpenedSession();
        return speciality;
    }

    public List<Speciality> getAllSpecs() {
        List<Speciality> specialities = (List<Speciality>) DAO.getAllObjects(Speciality.class);
        DAO.closeOpenedSession();
        return specialities;
    }

    public Speciality updateSpecName(Long id, String name) {
        Speciality speciality = getSpecById(id);
        if (speciality != null) {
            speciality.setName(name);
            DAO.updateObject(speciality);
            return speciality;
        }
        return null;
    }

    public boolean deleteSpecById(Long id) {
        try {
            DAO.deleteObjectById(id, Speciality.class);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
