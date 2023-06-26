package com.geopokrovskiy.service;

import com.geopokrovskiy.model.Skill;
import com.geopokrovskiy.model.Speciality;
import com.geopokrovskiy.model.Status;
import com.geopokrovskiy.repository.SpecialityRepository;
import com.geopokrovskiy.repository.hibernate.SpecialityRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.List;

public class SpecialityServiceTest {

    private SpecialityRepository specialityRepository = Mockito.mock(SpecialityRepositoryImpl.class);
    @InjectMocks
    private SpecialityService specialityService = new SpecialityService(specialityRepository);

    private List<Speciality> getSpecs(){
        return List.of(new Speciality(1L,"S1"),
                new Speciality(2L,"S2"),
                new Speciality(3L,"S3"),
                new Speciality(4L, "S4", Status.DELETED));
    }

    private Speciality newSpeciality(){
        return new Speciality(1L, "S1");
    }

    @Test
    public void testAddNewSpec(){
        Speciality speciality = this.newSpeciality();
        Mockito.when(specialityRepository.addNew(speciality)).thenReturn(speciality);
        Assert.assertEquals(speciality, specialityService.addNewSpec(speciality));
        Assert.assertEquals(Status.ACTIVE, specialityService.addNewSpec(speciality).getStatus());
    }

    @Test
    public void testGetSpecById(){
        Speciality speciality = this.newSpeciality();
        Mockito.when(specialityRepository.getById(1L)).thenReturn(speciality);
        Assert.assertEquals(speciality, specialityService.getSpecById(1L));
    }

    @Test
    public void testGetAll(){
        Mockito.when(specialityRepository.getAll()).thenReturn(getSpecs());
        List<Speciality> result = specialityService.getAllSpecs();
        Assert.assertEquals(3, result.size()); // returns only the Status.ACTIVE specs
    }

    @Test
    public void testUpdateSkillName(){
        Speciality speciality = this.newSpeciality();
        Mockito.when(specialityRepository.getById(1L)).thenReturn(speciality);
        Mockito.when(specialityRepository.update(speciality)).thenReturn(speciality);
        Assert.assertEquals("S2", specialityService.updateSpecName(1L, "S2").getName());
    }

    @Test
    public void testDeleteSkillById(){
        Mockito.when(specialityRepository.delete(1L)).thenReturn(true);
        Mockito.when(specialityRepository.delete(-1L)).thenReturn(false);
        Assert.assertTrue(specialityService.deleteSpecById(1L));
        Assert.assertFalse(specialityService.deleteSpecById(-1L));
    }
}
