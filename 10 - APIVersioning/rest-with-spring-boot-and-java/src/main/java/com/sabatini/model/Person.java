package com.sabatini.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sabatini.data.vo.v1.PersonVO;
import com.sabatini.data.vo.v2.PersonVOV2;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 6)
    private String gender;

    @Column()
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDay;

    public Person() {
    }

    public Person(PersonVO personVO) {
        this.firstName = personVO.getFirstName();
        this.lastName = personVO.getLastName();
        this.address = personVO.getAddress();
        this.gender = personVO.getGender();
    }

    public Person(PersonVOV2 personVOV2) {
        this.firstName = personVOV2.getFirstName();
        this.lastName = personVOV2.getLastName();
        this.address = personVOV2.getAddress();
        this.gender = personVOV2.getGender();
        this.birthDay = personVOV2.getBirthDay();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(gender, person.gender) && Objects.equals(birthDay, person.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, gender, birthDay);
    }
}
