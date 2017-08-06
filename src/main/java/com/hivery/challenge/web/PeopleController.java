package com.hivery.challenge.web;

import com.hivery.challenge.service.ParanuaraInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class PeopleController {
    @Autowired
    ParanuaraInformationService paranuaraInformationService;


    @RequestMapping(method= RequestMethod.GET, path="company/{index}/employees")
    public String getPeopleNamesInCompany(@PathVariable(value="index") int index){
       return paranuaraInformationService.getPeopleNamesInCompany(index);
    }

    @RequestMapping(method= RequestMethod.GET, path="commonAliveBrownEyesFriends/{firstPeopleInx}/{firstPeopleInx}")
    public String getCommonAliveFriendsWithBrownEyes(@PathVariable(value="firstPeopleInx") int firstPeopleIndex, @PathVariable(value="firstPeopleInx") int secondPeopleIndex){


        return paranuaraInformationService.getCommonAliveFriendsWithBrownEyes(firstPeopleIndex, secondPeopleIndex);
    }

    @RequestMapping(method= RequestMethod.GET, path="people/{index}/FavoriteFruitsAndVegetables")
    public String getPeopleFavoriteFruitsAndVegetables(@PathVariable(value="index") int index){
        return paranuaraInformationService.getPeopleInformation(index);
    }




}
