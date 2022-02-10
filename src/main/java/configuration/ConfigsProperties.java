package configuration;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Mutable;
import org.aeonbits.owner.Reloadable;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:./src/test/resources/config/configs.properties"})
public interface ConfigsProperties extends Mutable, Accessible, Reloadable, Config {

    @Key("token")
    String token();

    @Key("baseUri")
    String baseUri();
}
