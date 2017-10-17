package court

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Player)
class PlayerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "A valid player is constructed"() {
        given:
            def player = new Player(name: 'James', phone: '120-1111')
        when: "validate is called"
            def result = player.validate();
        then: "it should be valid"
            result
    }

    void "A player without a name is constructed"() {
        given:
            def player = new Player(name: '', phone: '120-1111')
        when: "validate is called"
            def result = player.validate();
        then: "The name should be rejected"
            !result
            player.errors['name'].codes.contains('nullable')
    }

    void "A player without a phone is constructed"() {
        given:
            def player = new Player(name: 'James', phone: '')
        when: "validate is called"
            def result = player.validate()
        then: "The phone number should be rejected."
            !result
            player.errors['phone'].codes.contains('nullable')
    }

}
