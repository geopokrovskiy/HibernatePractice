package com.geopokrovskiy.service;

import com.geopokrovskiy.model.Skill;
import com.geopokrovskiy.model.Status;
import com.geopokrovskiy.repository.SkillRepository;
import com.geopokrovskiy.repository.hibernate.SkillRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class SkillService {

    private SkillRepository skillRepository;

    public SkillService() {
        this.skillRepository = new SkillRepositoryImpl();
    }

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill addNewSkill(Skill skill) {
        return this.skillRepository.addNew(skill);
    }

    public Skill getSkillById(Long id) {
        return this.skillRepository.getById(id);
    }

    public List<Skill> getAllSkills() {
        return this.skillRepository.getAll().stream().
                filter(s -> s.getStatus().equals(Status.ACTIVE)).
                collect(Collectors.toList());
    }

    public Skill updateSkillName(Long id, String name) {
        Skill skill = getSkillById(id);
        if (skill != null) {
            skill.setName(name);
            return this.skillRepository.update(skill);
        }
        return null;
    }

    public boolean deleteSkillById(Long id) {
        return this.skillRepository.delete(id);
    }

}
