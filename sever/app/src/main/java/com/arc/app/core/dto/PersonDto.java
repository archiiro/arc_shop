package com.arc.app.core.dto;

import com.arc.app.core.domain.ImagePath;
import com.arc.app.core.domain.Person;
import com.arc.app.core.dto.AdministrativeDto;
import com.arc.app.core.dto.ImagePathDto;
import com.arc.app.core.dto.ParameterDto;
import com.arc.app.core.dto.UserDto;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Integer gender;
    private String phoneNumber;
    private String idCard;
    private String cardIssueBy;
    private Date cardIssueDate;
    private String email;
    private String addressDetail;
    private Boolean locked;
    private ImagePathDto avatar;
    private ParameterDto country;
    private ParameterDto ethnic;
    private ParameterDto religion;
    private ParameterDto occupation;
    private AdministrativeDto address;
    private UserDto user;

    public PersonDto() {

    }

    public PersonDto(Person entity) {
        if(entity != null) {
            this.id = entity.getId();
            this.firstName = entity.getFirstName();
            this.lastName = entity.getLastName();
            this.birthDate = entity.getBirthDate();
            this.gender = entity.getGender();
            this.phoneNumber = entity.getPhoneNumber();
            this.idCard = entity.getIdCard();
            this.cardIssueBy = entity.getCardIssueBy();
            this.cardIssueDate = entity.getCardIssueDate();
            this.email = entity.getEmail();
            this.addressDetail = entity.getAddressDetail();
            this.locked = entity.getLocked();
            if(entity.getAvatar() != null) {
                this.avatar = new ImagePathDto(entity.getAvatar(), false);
            }
            if(entity.getCountry() != null) {
                this.country = new ParameterDto(entity.getCountry());
            }
            if(entity.getEthnic() != null) {
                this.ethnic = new ParameterDto(entity.getEthnic());
            }
            if(entity.getReligion() != null) {
                this.religion = new ParameterDto(entity.getReligion());
            }
            if(entity.getOccupation() != null) {
                this.occupation = new ParameterDto(entity.getOccupation());
            }
            if(entity.getAddress() != null) {
                this.address = new AdministrativeDto(entity.getAddress(), true);
            }
            if(entity.getUser() != null) {
                this.user = new UserDto(entity.getUser());
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCardIssueBy() {
        return cardIssueBy;
    }

    public void setCardIssueBy(String cardIssueBy) {
        this.cardIssueBy = cardIssueBy;
    }

    public Date getCardIssueDate() {
        return cardIssueDate;
    }

    public void setCardIssueDate(Date cardIssueDate) {
        this.cardIssueDate = cardIssueDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public ImagePathDto getAvatar() {
        return avatar;
    }

    public void setAvatar(ImagePathDto avatar) {
        this.avatar = avatar;
    }

    public ParameterDto getCountry() {
        return country;
    }

    public void setCountry(ParameterDto country) {
        this.country = country;
    }

    public ParameterDto getEthnic() {
        return ethnic;
    }

    public void setEthnic(ParameterDto ethnic) {
        this.ethnic = ethnic;
    }

    public ParameterDto getReligion() {
        return religion;
    }

    public void setReligion(ParameterDto religion) {
        this.religion = religion;
    }

    public ParameterDto getOccupation() {
        return occupation;
    }

    public void setOccupation(ParameterDto occupation) {
        this.occupation = occupation;
    }

    public AdministrativeDto getAddress() {
        return address;
    }

    public void setAddress(AdministrativeDto address) {
        this.address = address;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
