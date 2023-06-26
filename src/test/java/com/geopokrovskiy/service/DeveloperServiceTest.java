package com.geopokrovskiy.service;

import com.geopokrovskiy.model.Developer;
import com.geopokrovskiy.model.Skill;
import com.geopokrovskiy.model.Speciality;
import com.geopokrovskiy.model.Status;
import com.geopokrovskiy.repository.DeveloperRepository;
import com.geopokrovskiy.repository.hibernate.DeveloperRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.List;

public class DeveloperServiceTest {
    private DeveloperRepository developerRepository = Mockito.mock(DeveloperRepositoryImpl.class);

    @InjectMocks
    private DeveloperService developerService = new DeveloperService(developerRepository);

    private List<Developer> getDevelopers() {
        List<Skill> skills1 = List.of(new Skill("S11"), new Skill("S12"));
        List<Skill> skills2 = List.of(new Skill("S21"), new Skill("S22"));
        return List.of(new Developer(1L, "Name1", "Surname1",
                        skills1, new Speciality("Spec1")),
                new Developer(2L, "Name2", "Surname2",
                        skills2, new Speciality("Spec2")
                ),
                new Developer(3L, "Name3", "Surname3",
                        skills2, new Speciality("Spec3"), Status.DELETED
                )
        );
    }

    private List<Skill> getSkills(){
        return List.of(new Skill(1L,"S1"),
                new Skill(2L,"S2"),
                new Skill(3L,"S3"),
                new Skill(4L, "S4", Status.DELETED));
    }

    private Developer newDeveloper() {
        List<Skill> skills1 = List.of(new Skill("S11"), new Skill("S12"));
        return new Developer(1L, "Name", "Surname", skills1, new Speciality("spec1"));
    }

    @Test
    public void testAddNewDev() {
        Developer developer = this.newDeveloper();
        Mockito.when(developerRepository.addNew(developer)).thenReturn(developer);
        Assert.assertEquals(developer, developerService.addNewDev(developer));
        Assert.assertEquals(Status.ACTIVE, developerService.addNewDev(developer).getStatus());
    }

    @Test
    public void testGetDevById() {
        Developer developer = this.newDeveloper();
        Mockito.when(developerRepository.getById(1L)).thenReturn(developer);
        Assert.assertEquals(developer, developerService.getDevById(1L));
    }

    @Test
    public void testGetAll() {
        Mockito.when(developerRepository.getAll()).thenReturn(getDevelopers());
        List<Developer> result = developerService.getAllDevs();
        Assert.assertEquals(2, result.size()); // returns only the Status.ACTIVE devs
    }

    @Test
    public void testUpdateDevFirstName() {
        Developer developer = this.newDeveloper();
        Mockito.when(developerRepository.getById(1L)).thenReturn(developer);
        Mockito.when(developerRepository.update(developer)).thenReturn(developer);
        Assert.assertEquals("New first name", developerService.updateDevFirstName(1L, "New first name").getFirstName());
    }

    @Test
    public void testUpdateDevLastName() {
        Developer developer = this.newDeveloper();
        Mockito.when(developerRepository.getById(1L)).thenReturn(developer);
        Mockito.when(developerRepository.update(developer)).thenReturn(developer);
        Assert.assertEquals("New last name", developerService.updateDevLastName(1L, "New last name").getLastName());
    }

    @Test
    public void testUpdateDevSpeciality() {
        Developer developer = this.newDeveloper();
        Speciality newSpec = new Speciality("New speciality");
        Mockito.when(developerRepository.getById(1L)).thenReturn(developer);
        Mockito.when(developerRepository.update(developer)).thenReturn(developer);
        Assert.assertEquals(newSpec, developerService.updateDevSpeciality(1L, newSpec).getSpeciality());
    }

    @Test
    public void testUpdateDevSkills(){
        Developer developer = this.newDeveloper();
        List<Skill> newSkills = this.getSkills();
        Mockito.when(developerRepository.getById(1L)).thenReturn(developer);
        Mockito.when(developerRepository.update(developer)).thenReturn(developer);
        Assert.assertEquals(newSkills, developerService.updateDevSkills(1L, newSkills).getSkills());
    }

    @Test
    public void testDeleteSkillById() {
        Mockito.when(developerRepository.delete(1L)).thenReturn(true);
        Mockito.when(developerRepository.delete(-1L)).thenReturn(false);
        Assert.assertTrue(developerService.deleteDevById(1L));
        Assert.assertFalse(developerService.deleteDevById(-1L));
    }
}
