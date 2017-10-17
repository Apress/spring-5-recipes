package com.apress.springrecipes.court.feeds;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;


public class RSSFeedView extends AbstractRssFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {
        feed.setTitle("World Soccer Tournaments");
        feed.setDescription("FIFA World Soccer Tournament Calendar");
        feed.setLink("tennis.org");

        @SuppressWarnings({"unchecked"})
        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");

        feed.setLastBuildDate(tournamentList.stream().map( TournamentContent::getPublicationDate).sorted().findFirst().orElse(null) );
    }


    @Override
    protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        @SuppressWarnings({"unchecked"})
        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");

        return tournamentList.stream().map(this::toItem).collect(Collectors.toList());
    }

    private Item toItem(TournamentContent tournament) {
        Item item = new Item();
        item.setAuthor(tournament.getAuthor());
        item.setTitle(String.format("%s - Posted by %s", tournament.getName(), tournament.getAuthor()));
        item.setPubDate(tournament.getPublicationDate());
        item.setLink(tournament.getLink());
        return item;
    }
}
