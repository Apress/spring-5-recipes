package court

import java.util.Date;

class Reservation {

    static belongsTo = Player
    String courtName;
    Date date;
    Player player;
    String sportType;
    static constraints = {
        sportType(inList: ["Tennis", "Soccer"])
        date(validator: {
            if (it.getAt(Calendar.DAY_OF_WEEK) == "SUNDAY" &&
                    (it.getAt(Calendar.HOUR_OF_DAY) < 8 || it.getAt(Calendar.HOUR_OF_DAY) > 22)) {
                return ['invalid.holidayHour']
            } else if (it.getAt(Calendar.HOUR_OF_DAY) < 9 || it.getAt(Calendar.HOUR_OF_DAY) > 21) {
                return ['invalid.weekdayHour']
            }
        })
    }

}
