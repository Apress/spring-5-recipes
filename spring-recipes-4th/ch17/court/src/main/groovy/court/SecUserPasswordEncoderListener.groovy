package court

import grails.plugin.springsecurity.SpringSecurityService
import org.grails.datastore.mapping.core.Datastore
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEvent
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEventListener
import org.grails.datastore.mapping.engine.event.EventType
import org.grails.datastore.mapping.engine.event.PreInsertEvent
import org.grails.datastore.mapping.engine.event.PreUpdateEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEvent
import groovy.transform.CompileStatic

@CompileStatic
class SecUserPasswordEncoderListener extends AbstractPersistenceEventListener {

    @Autowired
    SpringSecurityService springSecurityService

    SecUserPasswordEncoderListener(final Datastore datastore) {
        super(datastore)
    }

    @Override
    protected void onPersistenceEvent(AbstractPersistenceEvent event) {
        if (event.entityObject instanceof SecUser) {
            SecUser u = (event.entityObject as SecUser)
            if (u.password && (event.eventType == EventType.PreInsert || (event.eventType == EventType.PreUpdate && u.isDirty('password')))) {
                event.getEntityAccess().setProperty("password", encodePassword(u.password))
            }
        }
    }

    @Override
    boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        eventType == PreUpdateEvent || eventType == PreInsertEvent
    }

    private String encodePassword(String password) {
        springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
}
