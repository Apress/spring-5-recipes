package court

import grails.test.mixin.*
import spock.lang.*

import java.time.LocalDateTime
import java.time.ZoneOffset

@TestFor(ReservationController)
@Mock( Reservation )
class ReservationControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        def calendar = LocalDateTime.of(2017, 10, 13, 12, 00)
                .toInstant(ZoneOffset.UTC)

        params["courtName"] = 'Tennis Court #1'
        params["sportType"] = "Tennis"
        params["date"] = Date.from(calendar)
        params["player"] = new Player(name: "J. Doe", phone: "555-432-1234")
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.reservationList
            model.reservationCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.reservation!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def reservation = new Reservation()
            reservation.validate()
            controller.save(reservation)

        then:"The create view is rendered again with the correct model"
            model.reservation!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            reservation = new Reservation(params)

            controller.save(reservation)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/reservation/show/1'
            controller.flash.message != null
            Reservation.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def reservation = new Reservation(params)
            controller.show(reservation)

        then:"A model is populated containing the domain instance"
            model.reservation == reservation
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def reservation = new Reservation(params)
            controller.edit(reservation)

        then:"A model is populated containing the domain instance"
            model.reservation == reservation
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/reservation/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def reservation = new Reservation()
            reservation.validate()
            controller.update(reservation)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.reservation == reservation

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            reservation = new Reservation(params).save(flush: true)
            controller.update(reservation)

        then:"A redirect is issued to the show action"
            reservation != null
            response.redirectedUrl == "/reservation/show/$reservation.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/reservation/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def reservation = new Reservation(params).save(flush: true)

        then:"It exists"
            Reservation.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(reservation)

        then:"The instance is deleted"
            Reservation.count() == 0
            response.redirectedUrl == '/reservation/index'
            flash.message != null
    }
}
