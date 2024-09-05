package top.guyimaple.quick.core;

import lombok.Getter;
import top.guyimaple.quick.common.QuickService;
import top.guyimaple.quick.common.current.CurrentContext;
import top.guyimaple.quick.common.entry.Project;
import top.guyimaple.quick.common.writer.ProjectFileWriter;
import top.guyimaple.quick.core.repository.ServiceRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 工作空间
 * @author guyi
 * @date 2024/9/4
 */
public class QuickWorkspace {

 @Getter
 private final Project project;
 private final ServiceRepository serviceRepository;
 private final Map<String, QuickService> services = new HashMap<>();
 public QuickWorkspace(String name, String base, String web, String service, ProjectFileWriter writer, ServiceRepository serviceRepository) {
  this.project = new Project(name, base, web, service, writer, new CurrentContext());
  this.project.getContext().setProject(this.project);
  this.serviceRepository = serviceRepository;
 }

 public QuickService getService(String serviceName) {
  return Optional.ofNullable(services.get(serviceName))
          .orElseGet(() -> {
           QuickService s = serviceRepository.getService(serviceName);
           services.put(serviceName, s);
           return s;
          });
 }

}
