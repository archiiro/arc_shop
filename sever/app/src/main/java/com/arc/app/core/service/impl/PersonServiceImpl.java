package com.arc.app.core.service.impl;

import com.arc.app.core.domain.*;
import com.arc.app.core.dto.PersonDto;
import com.arc.app.core.other.Constants;
import com.arc.app.core.repository.*;
import com.arc.app.core.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository repository;

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private ImagePathRepository imagePathRepository;

    @Autowired
    private AdministrativeRepository administrativeRepository;

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public PersonDto find(Long id) {
        if (id != null) {
            Person person = repository.find(id);
            if (person != null) {
                return new PersonDto(person);
            }
        }
        return null;
    }

    @Override
    public PersonDto save(PersonDto dto) {
        if (dto == null) {
            return null;
        }
        Person person = null;
        if (dto.getId() != null) {
            person = repository.find(dto.getId());
        }
        if (person == null) {
            person = new Person();
        }
        if (dto.getFirstName() != null) {
            person.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            person.setLastName(dto.getLastName());
        }
        if (dto.getBirthDate() != null) {
            person.setBirthDate(dto.getBirthDate());
        }
        if (dto.getGender() != null) {
            person.setGender(dto.getGender());
        }
        if (dto.getPhoneNumber() != null) {
            person.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getIdCard() != null) {
            person.setIdCard(dto.getIdCard());
        }
        if (dto.getCardIssueBy() != null) {
            person.setCardIssueBy(dto.getCardIssueBy());
        }
        if (dto.getCardIssueDate() != null) {
            person.setCardIssueDate(dto.getCardIssueDate());
        }
        if (dto.getEmail() != null) {
            person.setEmail(dto.getEmail());
        }
        if (dto.getAddressDetail() != null) {
            person.setAddressDetail(dto.getAddressDetail());
        }
        if (dto.getCountry() != null && dto.getCountry().getId() != null) {
            Parameter country = parameterRepository.find(dto.getCountry().getId());
            if (country != null) {
                person.setCountry(country);
            }
        }
        if (dto.getEthnic() != null && dto.getEthnic().getId() != null) {
            Parameter ethnic = parameterRepository.find(dto.getEthnic().getId());
            if (ethnic != null) {
                person.setEthnic(ethnic);
            }
        }
        if (dto.getReligion() != null && dto.getReligion().getId() != null) {
            Parameter religion = parameterRepository.find(dto.getReligion().getId());
            if (religion != null) {
                person.setCountry(religion);
            }
        }
        if (dto.getOccupation() != null && dto.getOccupation().getId() != null) {
            Parameter occupation = parameterRepository.find(dto.getOccupation().getId());
            if (occupation != null) {
                person.setCountry(occupation);
            }
        }
        if (dto.getAddress() != null && dto.getAddress().getId() != null) {
            Administrative address = administrativeRepository.find(dto.getAddress().getId());
            if (address != null) {
                person.setAddress(address);
            }
        }
        if (dto.getUser() != null && dto.getUser().getId() != null) {
            User user = userRepository.find(dto.getUser().getId());
            if (user != null) {
                person.setUser(user);
            }
        }
        person = repository.save(person);
        return new PersonDto(person);
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null) {
            Person person = repository.find(id);
            if (person != null) {
                repository.delete(person);
                return true;
            }
        }
        return false;
    }

    @Override
    public PersonDto saveAvatar(Long id, MultipartFile file) {
        if (id != null) {
            try {
                String extension = file.getOriginalFilename().split("\\.(?=[^\\.]+$)")[1];
                UUID randomCode = UUID.randomUUID();
                String fileName = randomCode + "." + extension;
                try {
                    File fileToBeSaved = new File(Constants.LOCAL_PATH, fileName);
                    file.transferTo(fileToBeSaved);
                } catch (Exception e) {
                    logger.error("Error: ", e.getMessage(), e);
                }

                ImagePath avatar = new ImagePath();
                avatar.setExtension(extension);
                avatar.setType(file.getContentType());
                avatar.setSize(file.getSize());
                avatar.setName(fileName);
                Person person = repository.find(id);
                if (person != null) {
                    avatar = imagePathRepository.save(avatar);
                    person.setAvatar(avatar);
                    person = repository.save(person);
                    return new PersonDto(person);
                }
            } catch (Exception e) {
                logger.error("Error upload image: " + e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    @Override
    public PersonDto saveImageCard(Long id, List<MultipartFile> listFile) {
        if (id != null && listFile != null && listFile.size() > 0) {
            try {
                HashSet<ImagePath> imageCards = new HashSet<ImagePath>();
                Person person = repository.find(id);
                for (MultipartFile file : listFile) {
                    String extension = file.getOriginalFilename().split("\\.(?=[^\\.]+$)")[1];
                    UUID randomCode = UUID.randomUUID();
                    String fileName = randomCode + "." + extension;
                    try {
                        File fileToBeSaved = new File(Constants.LOCAL_PATH, fileName);
                        file.transferTo(fileToBeSaved);
                    } catch (Exception e) {
                        logger.error("Error: ", e.getMessage(), e);
                    }
                    ImagePath imageCard = new ImagePath();
                    imageCard.setExtension(extension);
                    imageCard.setType(file.getContentType());
                    imageCard.setSize(file.getSize());
                    imageCard.setName(fileName);
                    if(person != null) {
                        imageCard.setPerson(person);
                    }
                    imageCards.add(imageCard);
                }
                if (person != null) {
                    if (imageCards != null && imageCards.size() > 0) {
                        for (ImagePath item : imageCards) {
                            imagePathRepository.save(item);
                        }
                    }
                    if (person.getImageCards() != null) {
                        person.getImageCards().clear();
                        person.getImageCards().addAll(imageCards);
                    } else {
                        person.setImageCards(imageCards);
                    }
                    person = repository.save(person);
                    return new PersonDto(person);
                }
            } catch (Exception e) {
                logger.error("Error: ", e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public void makeDelete(Long id) {
        if(id != null) {
            Person person = repository.find(id);
            if(person != null) {
                person.setLocked(true);
            }
        }
    }
}
