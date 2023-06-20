package com.geopokrovskiy.service;

import com.geopokrovskiy.DAO.DAO;
import com.geopokrovskiy.model.Developer;
import com.geopokrovskiy.model.Skill;
import com.geopokrovskiy.model.Speciality;

import java.util.List;

public class DeveloperService {

    public Developer addNewDev(Developer developer) {
        DAO.addObject(developer);
        return developer;
    }

    public Developer getDevById(Long id) {
        Developer developer = (Developer) DAO.getObjectById(id, Developer.class);
        DAO.closeOpenedSession();
        return developer;
    }

    public List<Developer> getAllDevs() {
        List<Developer> devs = (List<Developer>) DAO.getAllObjects(Developer.class);
        return devs;
    }

    public Developer updateDevFirstName(Long id, String firstName) {
        Developer developer = getDevById(id);
        if (developer != null) {
            developer.setFirstName(firstName);
            DAO.updateObject(developer);
            return developer;
        }
        return null;
    }

    public Developer updateDevLastName(Long id, String lastName) {
        Developer developer = getDevById(id);
        if (developer != null) {
            developer.setLastName(lastName);
            DAO.updateObject(developer);
            return developer;
        }
        return null;
    }

    public Developer updateDevSpeciality(Long id, Speciality speciality) {
        Developer developer = getDevById(id);
        if (developer != null) {
            developer.setSpeciality(speciality);
            DAO.updateObject(developer);
            return developer;
        }
        return null;
    }

    public Developer updateDevSkills(Long id, List<Skill> skills) {
        Developer developer = getDevById(id);
        if (developer != null) {
            developer.setSkills(skills);
            DAO.updateObject(developer);
            return developer;
        }
        return null;
    }

    public boolean deleteDevById(Long id) {
        try {
            DAO.deleteObjectById(id, Developer.class);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
