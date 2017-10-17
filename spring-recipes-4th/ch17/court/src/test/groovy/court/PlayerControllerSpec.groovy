package court

import grails.test.mixin.*
import spock.lang.*

@TestFor(PlayerController)
@Mock(Player)
class PlayerControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["name"] = 'J. Doe'
        params["phone"] = '555-123-4567'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.playerList
            model.playerCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.player!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def player = new Player()
            player.validate()
            controller.save(player)

        then:"The create view is rendered again with the correct model"
            model.player!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            player = new Player(params)

            controller.save(player)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/player/show/1'
            controller.flash.message != null
            Player.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def player = new Player(params)
            controller.show(player)

        then:"A model is populated containing the domain instance"
            model.player == player
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def player = new Player(params)
            controller.edit(player)

        then:"A model is populated containing the domain instance"
            model.player == player
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/player/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def player = new Player()
            player.validate()
            controller.update(player)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.player == player

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            player = new Player(params).save(flush: true)
            controller.update(player)

        then:"A redirect is issued to the show action"
            player != null
            response.redirectedUrl == "/player/show/$player.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/player/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def player = new Player(params).save(flush: true)

        then:"It exists"
            Player.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(player)

        then:"The instance is deleted"
            Player.count() == 0
            response.redirectedUrl == '/player/index'
            flash.message != null
    }
}
