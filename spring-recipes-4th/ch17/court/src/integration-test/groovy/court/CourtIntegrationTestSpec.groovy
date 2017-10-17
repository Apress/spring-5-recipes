package court


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class CourtIntegrationTestSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void testQueries() {
        given: "2 Existing Players"
        // Define and save players
        def players = [ new Player(name:'James',phone:'120-1111'),
                        new Player(name:'Martha',phone:'999-9999')]
        players*.save()

        // Confirm two players are saved in the database
        Player.list().size() == 2
        when: "Player James is retrieved"
        // Get player from the database by name
        def testPlayer = Player.findByName('James')
        then:  "The phone number should match"
        // Confirm phone
        testPlayer.phone == '120-1111'
        when: "Player James is Updated"
        // Update player name
        testPlayer.name = 'Marcus'
        testPlayer.save()

        then: "The name should be updated in the DB"
        // Get updated player from the database, but now by phone
        def updatedPlayer = Player.findByPhone('120-1111')

        // Confirm name
        updatedPlayer.name == 'Marcus'

        when: "The updated player is deleted"
        // Delete player
        updatedPlayer.delete()

        then: "The player should be removed from the DB."
        // Confirm one player is left in the database
        Player.list().size() == 1

        // Confirm updatedPlayer is deleted
        def nonexistantPlayer = Player.findByPhone('120-1111')
        nonexistantPlayer == null
    }
}
