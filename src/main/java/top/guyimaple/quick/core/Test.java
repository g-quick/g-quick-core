package top.guyimaple.quick.core;

import top.guyimaple.quick.common.QuickService;
import top.guyimaple.quick.core.writer.LocalProjectFileWriter;
import top.guyimaple.quick.core.repository.DefaultLocalServiceRepository;

import java.net.MalformedURLException;
import java.util.Collections;

/**
 * @author guyi
 * @date 2024/9/4
 */
public class Test {

    public static void main(String[] args) throws MalformedURLException {
        QuickWorkspace workspace = new QuickWorkspace(
                "quick-test",
                "../test",
                "web",
                "service",
                new LocalProjectFileWriter("../test", "web", "service"),
                new DefaultLocalServiceRepository("../g-quick-repository")
        );
        QuickService service = workspace.getService("default-framework");
        service.main(workspace.getProject(), Collections.singletonMap("package", "top.guyi.quick.test"));
    }

}
