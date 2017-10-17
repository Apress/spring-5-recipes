package court

class WelcomeController {
    Date now = new Date()

    def index() { [today:now]}
}
