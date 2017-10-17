package court

class Player {

    static hasMany = [reservations: Reservation]
    String name
    String phone

    static constraints = {
        name(blank:false)
        phone(blank:false)
    }
}
