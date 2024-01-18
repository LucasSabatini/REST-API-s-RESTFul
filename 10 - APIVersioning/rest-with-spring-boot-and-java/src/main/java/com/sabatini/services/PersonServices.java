package com.sabatini.services;

import com.sabatini.data.vo.v1.PersonVO;
import com.sabatini.data.vo.v2.PersonVOV2;
import com.sabatini.exceptions.ResourceNotFoundException;
import com.sabatini.model.Person;
import com.sabatini.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVOV2> findAll() {
        logger.info("Finding all people!");

        return repository.findAll().stream().map(PersonVOV2::new).toList();
    }

    public PersonVOV2 findById(Long id) {
        logger.info("Finding one person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return new PersonVOV2(entity);
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating one person!");

        Person entity = new Person(personVO);
        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        repository.save(entity);

        PersonVO vo = new PersonVO(entity);
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 personVOV2) {
        logger.info("Creating one person with V2!");

        Person entity = new Person(personVOV2);
        entity.setFirstName(personVOV2.getFirstName());
        entity.setLastName(personVOV2.getLastName());
        entity.setAddress(personVOV2.getAddress());
        entity.setGender(personVOV2.getGender());
        entity.setBirthDay(personVOV2.getBirthDay());

        repository.save(entity);

        PersonVOV2 vo = new PersonVOV2(entity);
        return vo;
    }

    public PersonVOV2 update(PersonVOV2 personVOV2) {
        logger.info("Updating one person!");

        Person entity = repository.findById(personVOV2.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personVOV2.getFirstName());
        entity.setLastName(personVOV2.getLastName());
        entity.setAddress(personVOV2.getAddress());
        entity.setGender(personVOV2.getGender());
        entity.setBirthDay(personVOV2.getBirthDay());

        repository.save(entity);

        PersonVOV2 vo = new PersonVOV2(entity);
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}
