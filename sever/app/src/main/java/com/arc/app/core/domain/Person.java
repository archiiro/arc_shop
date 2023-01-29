package com.arc.app.core.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tbl_person")
@XmlRootElement

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "gender")
    private Integer gender; // 1: Nam, 2: Nu

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "card_issue_by")
    private String cardIssueBy;

    @Column(name = "card_issue_date")
    private Date cardIssueDate;

    @Column(name = "email")
    private String email;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "locked")
    private Boolean isLocked;

    @ManyToOne
    @JoinColumn(name = "id_image_path")
    private ImagePath avatar;

    @ManyToOne
    @JoinColumn(name = "id_country")
    private Parameter country; // type = 1

    @ManyToOne
    @JoinColumn(name = "id_ethnic")
    private Parameter ethnic; // type = 2

    @ManyToOne
    @JoinColumn(name = "id_religion")
    private Parameter religion; // type = 3

    @ManyToOne
    @JoinColumn(name = "id_occupation")
    private Parameter occupation; // type = 4

    @ManyToOne
    @JoinColumn(name = "id_administrative")
    private Administrative address;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<ImagePath> imageCards;

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

    public ImagePath getAvatar() {
        return avatar;
    }

    public void setAvatar(ImagePath avatar) {
        this.avatar = avatar;
    }

    public Parameter getCountry() {
        return country;
    }

    public void setCountry(Parameter country) {
        this.country = country;
    }

    public Parameter getEthnic() {
        return ethnic;
    }

    public void setEthnic(Parameter ethnic) {
        this.ethnic = ethnic;
    }

    public Parameter getReligion() {
        return religion;
    }

    public void setReligion(Parameter religion) {
        this.religion = religion;
    }

    public Parameter getOccupation() {
        return occupation;
    }

    public void setOccupation(Parameter occupation) {
        this.occupation = occupation;
    }

    public Administrative getAddress() {
        return address;
    }

    public void setAddress(Administrative address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Set<ImagePath> getImageCards() {
        return imageCards;
    }

    public void setImageCards(Set<ImagePath> imageCards) {
        this.imageCards = imageCards;
    }
}
