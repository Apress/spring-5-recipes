//FINAL 
package com.apress.springrecipes.court.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apress.springrecipes.court.feeds.TournamentContent;


@Controller
public class FeedController {
    @RequestMapping("/atomfeed")
    public String getAtomFeed(Model model) {
        List<TournamentContent> tournamentList = new ArrayList<>();
        tournamentList.add(TournamentContent.of("ATP", new Date(), "Australian Open", "www.australianopen.com"));
        tournamentList.add(TournamentContent.of("ATP", new Date(), "Roland Garros", "www.rolandgarros.com"));
        tournamentList.add(TournamentContent.of("ATP", new Date(), "Wimbledon", "www.wimbledon.org"));
        tournamentList.add(TournamentContent.of("ATP", new Date(), "US Open", "www.usopen.org"));
        model.addAttribute("feedContent", tournamentList);

        return "atomfeedtemplate";
    }

    @RequestMapping("/rssfeed")
    public String getRSSFeed(Model model) {
        List<TournamentContent> tournamentList;
        tournamentList = new ArrayList<>();
        tournamentList.add(TournamentContent.of("FIFA", new Date(), "World Cup", "www.fifa.com/worldcup/"));
        tournamentList.add(TournamentContent.of("FIFA", new Date(), "U-20 World Cup", "www.fifa.com/u20worldcup/"));
        tournamentList.add(TournamentContent.of("FIFA", new Date(), "U-17 World Cup", "www.fifa.com/u17worldcup/"));
        tournamentList.add(TournamentContent.of("FIFA", new Date(), "Confederations Cup", "www.fifa.com/confederationscup/"));
        model.addAttribute("feedContent", tournamentList);

        return "rssfeedtemplate";
    }

    @RequestMapping("/jsontournament")
    public String getJSON(Model model) {
        List<TournamentContent> tournamentList = new ArrayList<>();
        tournamentList.add(TournamentContent.of("FIFA", new Date(), "World Cup", "www.fifa.com/worldcup/"));
        tournamentList.add(TournamentContent.of("FIFA", new Date(), "U-20 World Cup", "www.fifa.com/u20worldcup/"));
        tournamentList.add(TournamentContent.of("FIFA", new Date(), "U-17 World Cup", "www.fifa.com/u17worldcup/"));
        tournamentList.add(TournamentContent.of("FIFA", new Date(), "Confederations Cup", "www.fifa.com/confederationscup/"));
        model.addAttribute("feedContent", tournamentList);

        return "jsontournamenttemplate";
    }
}
