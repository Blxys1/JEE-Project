package tn.esb.siad.eventAgency.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esb.siad.eventAgency.Domains.Event;

@Repository
//JpaRepository is a generic interface that takes two parameters
//the first one is the type of the entity (class name)
//the second one is the type of the primary key
public interface EventRepository extends JpaRepository<Event, Long>{
    //JpaRepository has a lot of methods that we can use
    //we can also create our own methods and the ORM will implement them for us
    //we can use language like JPQL or DSL to create our own methods
    //list of JpaRepository methods:
    // S is Event
    //T is the type of the primary key (Long)
    //1- S save(S entity);
    //this method is used to add a new entity to the database
    //example: add a new event e1
    //Event e1=new Event();
    //e1.setName("event1");
    //....
    //eventRepository.save(e1);
    //<=> in SQL : INSERT INTO event (name,description,theme,date,budget,nb_places,location_id) VALUES (?,?,?,?,?,?,?)
    //2- Collection<S> findAll();
    //this method is used to get all the entities from the database
    //3- Optional<S> findById(T id);
    //this method is used to get an entity by its id
    //the return type is Optional because the entity may not exist
    //Optional is a class that contains the entity if it exists or null if it doesn't exist
    //4- void deleteById(T id);
    //this method is used to delete an entity by its id
    //...
    //Rq: to update an entity by its id, we can use the save method
    //if the id of the entity exists in the database, the save method will update the entity
    //if not it will add a new entity
    //5- List<S> findAll(Sort sort);
    //this method is used to get all the entities from the database and sort them
    //Sort is a class that contains the sorting criteria
    //for example, we can sort the events by name
    //Sort sort=Sort.by("name");
    //List<Event> events=eventRepository.findAll(sort);

    //if we want to create our own method, we have to follow some rules:

    //1- the method name must start with findBy
    //2- the method name must end with the name of the attribute that we want to use to filter the results
    //example: if we want to get all the events that have a name that starts with "event"
    //we have to create a method with the name findBy and the attribute name
    //List<Event> events=eventRepository.findByName("event");
}
