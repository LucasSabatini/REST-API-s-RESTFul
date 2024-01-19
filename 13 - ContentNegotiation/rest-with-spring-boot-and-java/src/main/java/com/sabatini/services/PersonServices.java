package com.sabatini.services;

import com.sabatini.data.vo.v1.PersonVO;
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

    public List<PersonVO> findAll() {
        logger.info("Finding all people!");

        return repository.findAll().stream().map(PersonVO::new).toList();
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return new PersonVO(entity);
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

    public PersonVO update(PersonVO personVO) {
        logger.info("Updating one person!");

        Person entity = repository.findById(personVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        repository.save(entity);

        PersonVO vo = new PersonVO(entity);
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }
}
