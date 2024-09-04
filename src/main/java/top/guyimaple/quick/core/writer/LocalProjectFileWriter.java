package top.guyimaple.quick.core.writer;

import lombok.SneakyThrows;
import top.guyimaple.quick.common.entry.Project;
import top.guyimaple.quick.common.enums.ProjectType;
import top.guyimaple.quick.common.writer.ProjectFileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author guyi
 * @date 2024/8/30
 */
public class LocalProjectFileWriter implements ProjectFileWriter {

    private final String base;
    private final String web;
    private final String service;
    public LocalProjectFileWriter(String base, String web, String service) {
        this.base = base;
        this.web = web;
        this.service = service;
    }

    @Override
    @SneakyThrows
    public void write(ProjectType type, String fileName, byte[] content) {
        Path file = Paths.get(
                base,
                Objects.equals(ProjectType.WEB, type) ? web : service,
                fileName
        );
        Files.write(file, content);
    }

    @Override
    @SneakyThrows
    public void createDirectory(ProjectType type, String name) {
        Path file = Paths.get(
                base,
                Objects.equals(ProjectType.WEB, type) ? web : service,
                name
        );
        if (!Files.exists(file)) {
            Files.createDirectories(file);
        }
    }

}
