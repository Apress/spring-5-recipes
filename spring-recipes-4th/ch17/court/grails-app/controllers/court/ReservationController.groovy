package court

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ReservationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Reservation.list(params), model:[reservationCount: Reservation.count()]
    }

    def show(Reservation reservation) {
        respond reservation
    }

    def create() {
        respond new Reservation(params)
    }

    @Transactional
    def save(Reservation reservation) {
        if (reservation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reservation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reservation.errors, view:'create'
            return
        }

        reservation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservation.id])
                redirect reservation
            }
            '*' { respond reservation, [status: CREATED] }
        }
    }

    def edit(Reservation reservation) {
        respond reservation
    }

    @Transactional
    def update(Reservation reservation) {
        if (reservation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (reservation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond reservation.errors, view:'edit'
            return
        }

        reservation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservation.id])
                redirect reservation
            }
            '*'{ respond reservation, [status: OK] }
        }
    }

    @Transactional
    def delete(Reservation reservation) {

        if (reservation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        reservation.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservation.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
