package top.guyimaple.quick.core.repository;

import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * @author guyi
 * @date 2024/9/4
 */
public class DefaultLocalServiceRepository implements ServiceRepository {

    private final File base;
    public DefaultLocalServiceRepository(String base) {
        this.base = Paths.get(base).toFile();
    }

    private void list(File file, Function<File, Boolean> filter, List<File> files) {
        File[] fs = file.listFiles();
        if (fs != null) {
            for (File f : fs) {
                if (f.isDirectory()) {
                    this.list(f, filter, files);
                } else if (filter.apply(f)) {
                    files.add(f);
                }
            }
        }
    }

    @Override
    @SneakyThrows
    public URL getUrl(String serviceName) {
        List<File> files = new LinkedList<>();
        this.list(this.base, f -> f.getName().startsWith(serviceName) && f.getName().endsWith(".jar"), files);
        File file = files.stream().findFirst().orElse(null);
        if (file == null) {
            return null;
        }
        return file.toURI().toURL();
    }

}
