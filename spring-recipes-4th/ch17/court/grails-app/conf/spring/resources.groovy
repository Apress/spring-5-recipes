import court.SecUserPasswordEncoderListener
import org.springframework.web.servlet.i18n.SessionLocaleResolver

// Place your Spring DSL code here
beans = {
    secUserPasswordEncoderListener(SecUserPasswordEncoderListener, ref('hibernateDatastore'))
    localeResolver(SessionLocaleResolver) {
        defaultLocale= Locale.ENGLISH
        Locale.setDefault (Locale.ENGLISH)
    }
}
