package church.radmacher.marin.utils;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;

import java.io.IOException;

public class Docker {

    public static void runDocker() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
    }
}
