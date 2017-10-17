package com.apress.springrecipes.court.feeds;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;


public class AtomFeedView extends AbstractAtomFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {
        feed.setId("tag:tennis.org");
        feed.setTitle("Grand Slam Tournaments");

        @SuppressWarnings({"unchecked"})
        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");

        feed.setUpdated(tournamentList.stream().map(TournamentContent::getPublicationDate).sorted().findFirst().orElse(null));

    }

    @Override
    protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        @SuppressWarnings({"unchecked"})
        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");
        return tournamentList.stream().map(this::toEntry).collect(Collectors.toList());
    }

    private Entry toEntry(TournamentContent tournament) {
        Entry entry = new Entry();
        String date = String.format("%1$tY-%1$tm-%1$td", tournament.getPublicationDate());
        entry.setId(String.format("tag:tennis.org,%s:%d", date, tournament.getId()));
        entry.setTitle(String.format("%s - Posted by %s", tournament.getName(), tournament.getAuthor()));
        entry.setUpdated(tournament.getPublicationDate());

        Content summary = new Content();
        summary.setValue(String.format("%s - %s", tournament.getName(), tournament.getLink()));
        entry.setSummary(summary);
        return entry;
    }
}
