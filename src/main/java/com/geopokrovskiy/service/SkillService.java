package com.geopokrovskiy.service;

import com.geopokrovskiy.DAO.DAO;
import com.geopokrovskiy.model.Developer;
import com.geopokrovskiy.model.Skill;

import java.util.List;

public class SkillService {

    public Skill addNewSkill(Skill skill) {
        DAO.addObject(skill);
        return skill;
    }

    public Skill getSkillById(Long id) {
        Skill skill = (Skill) DAO.getObjectById(id, Skill.class);
        DAO.closeOpenedSession();
        return skill;
    }

    public List<Skill> getAllSkills() {
        List<Skill> skills = (List<Skill>) DAO.getAllObjects(Skill.class);
        DAO.closeOpenedSession();
        return skills;
    }

    public Skill updateSkillName(Long id, String name) {
        Skill skill = getSkillById(id);
        if (skill != null) {
            skill.setName(name);
            DAO.updateObject(skill);
            return skill;
        }
        return null;
    }

    public boolean deleteSkillById(Long id) {
        try {
            DAO.deleteObjectById(id, Skill.class);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
