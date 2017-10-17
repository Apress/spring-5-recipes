package court

class BootStrap {

    def init = { servletContext ->

        def adminRole = new court.SecRole(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new court.SecRole(authority: 'ROLE_USER').save(flush: true)

        def testUser = new  court.SecUser(username: 'user', password: 'password')
        testUser.save(flush: true)

        def testAdmin = new  court.SecUser(username: 'admin', password: 'secret')
        testAdmin.save(flush: true)

        court.SecUserSecRole.create testUser, userRole, true
        court.SecUserSecRole.create testAdmin, adminRole, true


    }
    def destroy = {
    }
}
