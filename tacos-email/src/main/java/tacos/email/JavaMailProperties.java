package tacos.email;

import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class JavaMailProperties extends Properties {
    public JavaMailProperties(){
        super();
        setProperty("mail.imap.ssl.protocols", "TLSv1.1");
        setProperty("mail.imap.starttls.required", "true");
        setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        setProperty("mail.imap.socketFactory.fallback", "false");
        setProperty("mail.store.protocol", "imaps");
        setProperty("mail.debug", "true");
    }
}
