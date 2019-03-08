package fr.softeam.opus.userskillmgmt.configuration;

import com.google.inject.AbstractModule;
import fr.softeam.opus.userskillmgmt.business.hello.HelloBlo;
import fr.softeam.opus.userskillmgmt.business.version.VersionBlo;
import fr.softeam.opus.userskillmgmt.services.HelloService;
import fr.softeam.opus.userskillmgmt.services.VersionService;

public class BeansBinderConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(HelloService.class).to(HelloBlo.class);
        bind(VersionService.class).to(VersionBlo.class);
    }
}
