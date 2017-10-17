package court

import grails.test.mixin.TestFor
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
class ReservationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void testReservation() {
        given:
        def calendar = LocalDateTime.of(2017, 10, 13, 15, 00)
                .toInstant(ZoneOffset.UTC)
        def validDateReservation = Date.from(calendar)

        def reservation = new Reservation(
                sportType:'Tennis', courtName:'Main',
                date:validDateReservation,player:new Player(name:'James',phone:'120-1111'))

        expect:
            reservation.validate()
    }

    void testOutOfRangeDateReservation() {
        given:
        def calendar = LocalDateTime.of(2017, 10, 13, 23, 00)
                .toInstant(ZoneOffset.UTC)

        def invalidDateReservation = Date.from(calendar)
        def reservation = new Reservation(
                sportType:'Tennis',courtName:'Main',
                date:invalidDateReservation,player:new Player(name:'James',phone:'120-1111'))

        expect:
            !reservation.validate()
            reservation.errors['date'].code == 'invalid.weekdayHour'
    }

    void testOutOfRangeSportTypeReservation() {
        given:
        def calendar = LocalDateTime.of(2017, 10, 13, 15, 00)
                .toInstant(ZoneOffset.UTC)
        def validDateReservation = Date.from(calendar)
        def reservation = new Reservation(
                sportType:'Baseball',courtName:'Main',
                date:validDateReservation,player:new Player(name:'James',phone:'120-1111'))

        expect:
            !reservation.validate()
            reservation.errors['sportType'].codes.contains('not.inList')
    }
}
