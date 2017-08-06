package com.hivery.challenge.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

@Repository
public class PeopleRepository {
    @Autowired
    MongoDatabase mongoDatabase;
    private static final String PEOPLE_DOCUMENT = "people";


    public Document getFavoriteFoodsOfPeopleByIndex(int index){
        MongoCollection<Document> collection = mongoDatabase.getCollection(PEOPLE_DOCUMENT);
        return collection.find(eq("index", index))
                .projection(fields(include("favouriteFood"), excludeId()))
                .first();


    }

    public Document getPeopleByIndex(int index){
        MongoCollection<Document> collection = mongoDatabase.getCollection(PEOPLE_DOCUMENT);
        return collection.find(eq("index", index))
                .projection(fields(include("name", "age", "address", "phone", "index"), excludeId()))
                .first();
    }

    public FindIterable<Document> getAllEmployeesByCompanyIndex(int companyIndex){
        MongoCollection<Document> collection = mongoDatabase.getCollection(PEOPLE_DOCUMENT);
        return collection.find(eq("company_id", companyIndex))
                .projection(fields(include("name", "age", "address", "phone", "index"), excludeId()));
    }

    public FindIterable<Document> listOfAliveCommonFriendsWtihBrownEyes(int peopleIndex1, int peopleIndex2){
        List<Integer> indexes = new ArrayList<>();
        Set<Map.Entry<String, Object>> result =new HashSet<>();
        indexes.add(peopleIndex1);
        indexes.add(peopleIndex2);
        MongoCollection<Document> collection = mongoDatabase.getCollection(PEOPLE_DOCUMENT);
        FindIterable<Document> resultIterable = collection.find(and(

                nin("index", indexes),
                all("friends.index", indexes),
                eq("eyeColor", "brown"),
                eq("has_died", false)))
                .projection(fields(include("name", "age", "address", "phone", "index"), excludeId()));


        return resultIterable;

    }
}
