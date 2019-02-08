package fr.softeam.opus.userskillmgmt.configuration;

import com.google.inject.AbstractModule;
import fr.softeam.opus.userskillmgmt.business.hello.HelloBloImpl;
import fr.softeam.opus.userskillmgmt.services.HelloService;

public class BeansBinderConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(HelloService.class).to(HelloBloImpl.class);
    }
}
