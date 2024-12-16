package com.etter.library.services;

import com.etter.library.exceptions.LibraryExceptions;
import com.etter.library.persistence.entities.Publisher;
import com.etter.library.persistence.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    @Transactional
    public void createPublisher(String name) throws LibraryExceptions {

        //verificar si ya existe
        //validar valor enviado por parámetro
        validate(name);

        Publisher newPublisher = new Publisher();

        //Pienso que debería buscar si el autor ya existe en la BBDD

        newPublisher.setName(name);

        publisherRepository.save(newPublisher);

    }

    public List<Publisher> getAllPublishers() {

        List<Publisher> publisherList = new ArrayList<>();
        publisherList = publisherRepository.findAll();

        return publisherList;
    }

    @Transactional
    public void editPublisher(String id, String name) throws LibraryExceptions {

        //validar parametros enviados
        validate(name);

        Optional<Publisher> result = publisherRepository.findById(id);

        if (result.isPresent()) {
            Publisher publisher = result.get();
            publisher.setName(name);
            publisherRepository.save(publisher);
        }

    }

    private void validate(String name) throws LibraryExceptions {
        if (name.isEmpty()) {
            throw new LibraryExceptions("The publisher's name can't be empty ");
        }

    }
}
