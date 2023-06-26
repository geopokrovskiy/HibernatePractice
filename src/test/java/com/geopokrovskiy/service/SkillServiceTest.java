package com.geopokrovskiy.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.geopokrovskiy.model.Skill;
import com.geopokrovskiy.model.Status;
import com.geopokrovskiy.repository.SkillRepository;
import com.geopokrovskiy.repository.hibernate.SkillRepositoryImpl;
import com.geopokrovskiy.service.SkillService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.List;

public class SkillServiceTest {
    private SkillRepository skillRepository = Mockito.mock(SkillRepositoryImpl.class);

    @InjectMocks
    private SkillService skillService = new SkillService(skillRepository);

    private List<Skill> getSkills(){
        return List.of(new Skill(1L,"S1"),
                new Skill(2L,"S2"),
                new Skill(3L,"S3"),
                new Skill(4L, "S4", Status.DELETED));
    }

    private Skill newSkill(){
        return new Skill(1L, "S1");
    }

    @Test
    public void testAddNewSkill(){
        Skill skill = this.newSkill();
        Mockito.when(skillRepository.addNew(skill)).thenReturn(skill);
        Assert.assertEquals(skill, skillService.addNewSkill(skill));
        Assert.assertEquals(Status.ACTIVE, skillService.addNewSkill(skill).getStatus());
    }

    @Test
    public void testGetSkillById(){
        Skill skill = this.newSkill();
        Mockito.when(skillRepository.getById(1L)).thenReturn(skill);
        Assert.assertEquals(skill, skillService.getSkillById(1L));
    }

    @Test
    public void testGetAll(){
        Mockito.when(skillRepository.getAll()).thenReturn(getSkills());
        List<Skill> result = skillService.getAllSkills();
        Assert.assertEquals(3, result.size()); // returns only the Status.ACTIVE skills
    }

    @Test
    public void testUpdateSkillName(){
        Skill skill = this.newSkill();
        Mockito.when(skillRepository.getById(1L)).thenReturn(skill);
        Mockito.when(skillRepository.update(skill)).thenReturn(skill);
        Assert.assertEquals("S2", skillService.updateSkillName(1L, "S2").getName());
    }

    @Test
    public void testDeleteSkillById(){
        Mockito.when(skillRepository.delete(1L)).thenReturn(true);
        Mockito.when(skillRepository.delete(-1L)).thenReturn(false);
        Assert.assertTrue(skillService.deleteSkillById(1L));
        Assert.assertFalse(skillService.deleteSkillById(-1L));
    }
}
