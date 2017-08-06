package com.hivery.challenge.service;

import com.hivery.challenge.repository.PeopleRepository;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ParanuaraInformationService {
    @Autowired
    PeopleRepository peopleRepository;
    private final Set<String> vegetables;
    private final Set<String> fruits;

    public ParanuaraInformationService() {
        fruits = new HashSet<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("orange");
        fruits.add("strawberry");
        vegetables = new HashSet<>();
        vegetables.add("beetroot");
        vegetables.add("carrot");
        vegetables.add("celery");
        vegetables.add("cucumber");
    }

    public String getPeopleInformation(int index) {
        Document peopleFoods = peopleRepository.getFavoriteFoodsOfPeopleByIndex(index);
        List<String> listOfFoods = (List<String>) peopleFoods.get("favouriteFood");
        List<String> favouriteFruits = new ArrayList<>();
        List<String> favouriteVegetables = new ArrayList<>();
        listOfFoods.forEach(food-> {
            if(fruits.contains(food))
                favouriteFruits.add(food);
            if(vegetables.contains(food))
                favouriteVegetables.add(food);
        });

        Document peopleInformation = peopleRepository.getPeopleByIndex(index);
        peopleInformation.append("fruits", favouriteFruits);
        peopleInformation.append("vegetables", favouriteVegetables);

        return peopleInformation.toJson(new JsonWriterSettings(JsonMode.SHELL,true));
    }

    public String getCommonAliveFriendsWithBrownEyes(int firstPeopleIndex,  int secondPeopleIndex){

        FindIterable<Document>  commonFriends = peopleRepository.listOfAliveCommonFriendsWtihBrownEyes(firstPeopleIndex, secondPeopleIndex);
        Document firstPeople = peopleRepository.getPeopleByIndex(firstPeopleIndex);
        Document secondPeople = peopleRepository.getPeopleByIndex(secondPeopleIndex);
        Document result = new Document();
        result.append("firstPeople", firstPeople);
        result.append("secondPeople", secondPeople);
        result.append("commonFriends", commonFriends);

        return result.toJson(new JsonWriterSettings(JsonMode.SHELL,true));
    }

    public String getPeopleNamesInCompany( int index){
        FindIterable<Document> allEmployees = peopleRepository.getAllEmployeesByCompanyIndex(index);
        Document result = new Document();
        result.append(String.format("all Employees of company %d", index), allEmployees);
        return result.toJson(new JsonWriterSettings(JsonMode.SHELL,true));

    }


}
